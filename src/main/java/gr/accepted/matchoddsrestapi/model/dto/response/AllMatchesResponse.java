package gr.accepted.matchoddsrestapi.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllMatchesResponse<T> {

    @JsonProperty(value = "matches")
    private List<T> matchResponseList;

    public AllMatchesResponse(List<T> matchResponseList) {
        this.matchResponseList = matchResponseList;
    }
}
