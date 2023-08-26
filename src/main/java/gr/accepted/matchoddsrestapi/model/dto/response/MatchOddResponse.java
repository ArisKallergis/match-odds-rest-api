package gr.accepted.matchoddsrestapi.model.dto.response;

import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MatchOddResponse {

    private Long id;
    private Character specifier;
    private BigDecimal odd;
    private Long matchId;

    public MatchOddResponse(MatchOdd matchOdd) {
        this.id = matchOdd.getId();
        this.specifier = matchOdd.getSpecifier();
        this.odd = matchOdd.getOdd();
        this.matchId = matchOdd.getMatch() != null ? matchOdd.getMatch().getId() : null;
    }
}
