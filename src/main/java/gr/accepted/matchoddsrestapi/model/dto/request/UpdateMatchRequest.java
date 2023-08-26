package gr.accepted.matchoddsrestapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.accepted.matchoddsrestapi.exception.IdMismatchException;
import gr.accepted.matchoddsrestapi.model.Sport;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateMatchRequest {

    private Long id;
    private String description;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate matchDate;

    @NotNull
    private LocalTime matchTime;

    @NotNull
    private String teamA;

    @NotNull
    private String teamB;

    @NotNull
    private Sport sport;

    private List<@Valid UpdateNestedMatchOddRequest> matchOdds = new ArrayList<>();

    public Match toEntity() {
        Match match = new Match();
        List<MatchOdd> matchOdds = this.matchOdds.stream()
                .map((matchOddRequest) -> {
                    if (matchOddRequest.getMatchId() == null) {
                        matchOddRequest.setMatchId(this.id);
                    } else {
                        if (!matchOddRequest.getMatchId().equals(this.id)) {
                            throw new IdMismatchException("Match odds should contain the correct match id");
                        }
                    }
                    return matchOddRequest.toEntity(match);
                }).toList();

        match.setId(this.id);
        match.setDescription(this.getDescription());
        match.setMatchDate(this.matchDate);
        match.setMatchTime(this.matchTime);
        match.setTeamA(this.teamA);
        match.setTeamB(this.teamB);
        match.setSport(this.sport);
        match.setMatchOdds(matchOdds);

        return match;
    }
}
