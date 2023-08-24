package gr.accepted.matchoddsrestapi.model.response;

import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import lombok.Data;

import java.util.List;

@Data
public class AllMatchOddsResponse {

    private List<MatchOdd> matchOdds;

    public AllMatchOddsResponse(List<MatchOdd> matchOdds) {
        this.matchOdds = matchOdds;
    }
}
