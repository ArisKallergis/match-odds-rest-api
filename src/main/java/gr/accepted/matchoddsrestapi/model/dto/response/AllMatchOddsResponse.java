package gr.accepted.matchoddsrestapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllMatchOddsResponse {

    @JsonProperty(value = "matchOdds")
    private List<MatchOddResponse> matchOddResponseList;

    public AllMatchOddsResponse(List<MatchOddResponse> matchOddResponseList) {
        this.matchOddResponseList = matchOddResponseList;
    }
}
