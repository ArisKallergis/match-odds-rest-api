package gr.accepted.matchoddsrestapi.model.dto.response;

import gr.accepted.matchoddsrestapi.model.enums.Sport;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class MatchResponse {

    @Schema(example = "4")
    private Long id;

    @Schema(example = "Some description")
    private String description;

    @Schema(example = "31/12/2023")
    private LocalDate matchDate;

    @Schema(example = "13:42")
    private LocalTime matchTime;

    @Schema(example = "OSFP")
    private String teamA;

    @Schema(example = "PAO")
    private String teamB;

    @Schema(examples = "FOOTBALL")
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
