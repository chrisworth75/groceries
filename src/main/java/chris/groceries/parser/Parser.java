package chris.groceries.parser;

import chris.groceries.domain.Result;
import chris.groceries.domain.ResultContainer;
import chris.groceries.exception.GroceryParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static String baseUrl = null;

    private static final Pattern energyPattern = Pattern.compile("([1-9][0-9]+)(kJ)");
    private static final Pattern pricePattern = Pattern.compile("(£)([0-9]+.[0-9]+)(/unit)");

    public static ResultContainer parse(String url) throws GroceryParseException {

        ResultContainer resultContainer = new ResultContainer();
        Document doc = loadDocument(url);

        StringBuilder sb = new StringBuilder(url);

        baseUrl = sb.substring(0, sb.lastIndexOf("/")) + "/";

        Elements productElements = doc.select("div.product");

        for (Element productElement : productElements) {

            // Get title
            Element titleElement = productElement.select("div.productNameAndPromotions a").first();
            String title = titleElement != null ? titleElement.text().trim() : null;

            // Get price
            Element priceElement = productElement.select("p.pricePerUnit").first();

            // Fetch Result Page for additional fields
            String productUrl = titleElement.attr("href");
            Element productPage = loadDocument(getAbsoluteUrl(productUrl));

            Integer energy = null;
            String description = null;

            Element energyElement = null;

            if (productPage != null) {

                Elements productPageDivs = productPage.select("div.mainProductInfo");

                // Get description
                Element descriptionElement = productPage.select("div.productText p").first();
                description = descriptionElement != null ? descriptionElement.text().trim() : null;

                // Get energy
                energyElement =
                        productPage.select("table.nutritionTable tbody tr:first-child td:nth-child(2)").first();

            }

            // Additional parsing/validation for price and energy
            if (energyElement != null) {
                Matcher energyMatcher = energyPattern.matcher(energyElement.text().trim());

                if (energyMatcher.find()) {
                    energy = Integer.parseInt(energyMatcher.group(1));
                }
            }

            Matcher priceMatcher = pricePattern.matcher(priceElement != null ? priceElement.text() : "");

            BigDecimal price = null;

            if (priceMatcher.find()) {

                try {
                    price = new BigDecimal(priceMatcher.group(2));
                } catch (NumberFormatException e) {
                    throw new GroceryParseException(e.getMessage());
                }
            }

            Result result = Result.builder()
                    .title(title)
                    .energy(energy)
                    .price(price)
                    .description(description)
                    .build();

            resultContainer.addResult(result);
        }
        return resultContainer;
    }

    private static String getAbsoluteUrl(String relativeUrl) {

        return baseUrl + relativeUrl;
    }

    public static Document loadDocument(String url) throws GroceryParseException{

        Document document;

        try{
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new GroceryParseException(e.getMessage());
        }

        if (document == null)
            throw new GroceryParseException("Couldn't parse document");

        return document;
    }
}
