package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;
import java.util.List;

@RepositoryRestResource
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByStartDateBetween(ZonedDateTime startDateOpen, ZonedDateTime startDateClose);
	List<Match> findByCancelationDeadlineBetween(ZonedDateTime from, ZonedDateTime to);
	Match save(Match persisted);
}
