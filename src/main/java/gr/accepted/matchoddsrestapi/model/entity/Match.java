package gr.accepted.matchoddsrestapi.model.entity;

import gr.accepted.matchoddsrestapi.model.enums.Sport;
import gr.accepted.matchoddsrestapi.model.enums.SportToDbConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "match")
public class Match {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_generator")
    @SequenceGenerator(name = "match_generator", sequenceName = "match_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    @Size(min = 1, max = 255)
    private String description;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "match_date")
    private LocalDate matchDate;

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "match_time")
    private LocalTime matchTime;

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "team_a")
    private String teamA;

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "team_b")
    private String teamB;

    @NotNull
    @Convert(converter = SportToDbConverter.class)
    @Column(name = "sport", columnDefinition = "int2")
    private Sport sport;

    @OneToMany(mappedBy = "match", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<@Valid MatchOdd> matchOdds = new ArrayList<>();
}
