package gr.accepted.matchoddsrestapi.model.dto.response;

import gr.accepted.matchoddsrestapi.model.entity.MatchOdd;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MatchOddResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "W")
    private String specifier;

    @Schema(example = "2.3")
    private BigDecimal odd;

    @Schema(example = "4")
    private Long matchId;

    public MatchOddResponse(MatchOdd matchOdd) {
        this.id = matchOdd.getId();
        this.specifier = matchOdd.getSpecifier();
        this.odd = matchOdd.getOdd();
        this.matchId = matchOdd.getMatch() != null ? matchOdd.getMatch().getId() : null;
    }
}
