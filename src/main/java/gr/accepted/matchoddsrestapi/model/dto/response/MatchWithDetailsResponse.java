package gr.accepted.matchoddsrestapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MatchWithDetailsResponse extends MatchResponse {

    @JsonProperty(value = "matchOdds")
    private List<MatchOddResponse> matchOddResponseList = new ArrayList<>();

    public MatchWithDetailsResponse(Match match) {
        super(match);
        this.matchOddResponseList = match.getMatchOdds().stream().map(MatchOddResponse::new).toList();
    }
}
