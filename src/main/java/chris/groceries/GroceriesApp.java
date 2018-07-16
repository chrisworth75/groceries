package chris.groceries;

import chris.groceries.domain.ResultContainer;
import chris.groceries.exception.GroceryParseException;
import chris.groceries.parser.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GroceriesApp {

    private static final String URL =
            "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/" +
                    "webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    public GroceriesApp() {

        try {

            ResultContainer resultContainer = Parser.parse(URL);
            ObjectMapper objectMapper = new ObjectMapper();

            System.out.println(objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(resultContainer));

        } catch (GroceryParseException | JsonProcessingException e) {
            System.out.println("Exception getting groceries: " + e.getMessage());
        }
    }

    public static void main(String... args) {

        GroceriesApp groceriesApp = new GroceriesApp();
    }
}
