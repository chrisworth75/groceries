package chris.groceries.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title", "energy", "price", "description" })
public class Result {

    private String title;
    @JsonProperty("kcal_per_100g")
    private Integer energy;
    @JsonProperty("unit_price")
    private BigDecimal price;
    private String description;
}
