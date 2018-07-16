package chris.groceries.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Getter
public class Total {

    private static final BigDecimal VAT_RATE = new BigDecimal(".2");

    private BigDecimal gross = new BigDecimal(0);

    public void add(BigDecimal amount) {

        gross = gross.add(amount);
    }

    @JsonProperty("vat")
    public String getVat() {
        BigDecimal vat = gross.multiply(VAT_RATE);
        return vat.round(new MathContext(2, RoundingMode.HALF_UP)).toString();
    }
}