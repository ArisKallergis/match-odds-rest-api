package gr.accepted.matchoddsrestapi.repository;

import gr.accepted.matchoddsrestapi.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {

}
