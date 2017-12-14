package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.List;

@RepositoryRestResource
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByStartDateBetween(@Param("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDateOpen,
									   @Param("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDateClose);
	List<Match> findByCancelationDeadlineBetween(ZonedDateTime from, ZonedDateTime to);
}
