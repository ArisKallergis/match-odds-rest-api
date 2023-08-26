package gr.accepted.matchoddsrestapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match_odd")
public class MatchOdd {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odd_generator")
    @SequenceGenerator(name = "match_odd_generator", sequenceName = "match_odd_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "specifier")
    private String specifier;

    @NotNull
    @Positive
    @Column(name = "odd")
    private BigDecimal odd;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "match_id")
    private Match match;
}
