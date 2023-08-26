package gr.accepted.matchoddsrestapi.model.dto.request;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateMatchOddRequest {

    @NotNull
    private Character specifier;

    @NotNull
    private BigDecimal odd;

    private Long matchId;

    public MatchOdd toEntity() {
        Match match = new Match();
        match.setId(this.matchId);
        return new MatchOdd(
                null,
                this.specifier,
                this.odd,
                match
        );
    }

    public MatchOdd toEntity(Match match) {
        return new MatchOdd(
                null,
                this.specifier,
                this.odd,
                match
        );
    }
}
