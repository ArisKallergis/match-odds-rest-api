package gr.accepted.matchoddsrestapi.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.accepted.matchoddsrestapi.model.enums.Sport;
import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreateMatchRequest {

    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate matchDate;

    @NotNull
    private LocalTime matchTime;

    @NotNull
    @Size(min = 2, max = 10)
    private String teamA;

    @NotNull
    @Size(min = 2, max = 10)
    private String teamB;

    @NotNull
    private Sport sport;

    @Valid
    private List<@Valid CreateNestedMatchOddRequest> matchOdds = new ArrayList<>();

    public Match toEntity() {
        Match match = new Match();
        List<MatchOdd> matchOdds = this.matchOdds.stream().map((matchOddRequest) -> matchOddRequest.toEntity(match)).toList();

        match.setId(null);
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
