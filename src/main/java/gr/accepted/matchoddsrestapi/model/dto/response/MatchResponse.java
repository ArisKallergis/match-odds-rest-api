package gr.accepted.matchoddsrestapi.model.dto.response;

import gr.accepted.matchoddsrestapi.model.Sport;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class MatchResponse {

    private Long id;
    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private Sport sport;

    public MatchResponse(Match match) {
        this.id = match.getId();
        this.description = match.getDescription();
        this.matchDate = match.getMatchDate();
        this.matchTime = match.getMatchTime();
        this.teamA = match.getTeamA();
        this.teamB = match.getTeamB();
        this.sport = match.getSport();
    }
}
