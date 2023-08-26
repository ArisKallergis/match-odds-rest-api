package gr.accepted.matchoddsrestapi.model.dto.request;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdateMatchOddRequest {

    private Long id;

    @NotNull
    @Size(min = 1, max = 1)
    private String specifier;

    @NotNull
    @Positive
    private BigDecimal odd;

    @NotNull
    private Long matchId;

    public MatchOdd toEntity() {
        Match match = new Match();
        match.setId(this.matchId);
        return new MatchOdd(
                this.id,
                this.specifier,
                this.odd,
                match
        );
    }
}
