package chris.groceries.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ResultContainer {

    private List<Result> results = new ArrayList<>();

    private Total total = new Total();

    public void addResult(Result result){

        results.add(result);
        total.add(result.getPrice());
    }
}
