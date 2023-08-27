package gr.accepted.matchoddsrestapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AllMatchesResponse<T> {

    @JsonProperty(value = "matches")
    private List<T> matchResponseList;

    public AllMatchesResponse(List<T> matchResponseList) {
        this.matchResponseList = matchResponseList;
    }

    // The below are for use in swagger
    public static class AllMatches extends AllMatchesResponse<MatchResponse> {

        public AllMatches(List<MatchResponse> matchResponseList) {
            super(matchResponseList);
        }
    }

    public static class AllMatchesDetails extends AllMatchesResponse<MatchResponse> {

        public AllMatchesDetails(List<MatchResponse> matchResponseList) {
            super(matchResponseList);
        }
    }
}
