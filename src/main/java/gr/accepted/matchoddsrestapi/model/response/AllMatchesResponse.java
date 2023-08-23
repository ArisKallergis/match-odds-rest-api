package gr.accepted.matchoddsrestapi.model.response;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import lombok.Data;

import java.util.List;

@Data
public class AllMatchesResponse {

    private List<Match> matches;

    public AllMatchesResponse(List<Match> matches) {
        this.matches = matches;
    }
}
