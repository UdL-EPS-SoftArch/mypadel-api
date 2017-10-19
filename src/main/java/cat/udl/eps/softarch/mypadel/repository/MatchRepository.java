package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.ZonedDateTime;
import java.util.List;

@RepositoryRestResource
public interface MatchRepository extends PagingAndSortingRepository<Match, Long> {
	List<Match> findByStartDateBetween(@Param("startDate")ZonedDateTime startDateOpen,
									   @Param("startDate")ZonedDateTime startDateClose);
}
