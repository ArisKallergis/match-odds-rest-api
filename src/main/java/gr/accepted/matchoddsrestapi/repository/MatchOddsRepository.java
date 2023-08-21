package gr.accepted.matchoddsrestapi.repository;

import gr.accepted.matchoddsrestapi.model.MatchOdds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchOddsRepository extends JpaRepository<MatchOdds, Long> {
}
