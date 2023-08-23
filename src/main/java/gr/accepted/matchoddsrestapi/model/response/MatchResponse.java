package gr.accepted.matchoddsrestapi.model.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import lombok.Data;

@Data
public class MatchResponse {

    private String name;
    private Integer age;

    public MatchResponse(Match match) {
        this.name = match.getName();
        this.age = match.getAge();
    }

    public MatchResponse(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
