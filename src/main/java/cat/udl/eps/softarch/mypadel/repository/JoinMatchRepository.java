package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface JoinMatchRepository extends PagingAndSortingRepository<JoinMatch, Long> {
	List<JoinMatch> findByMatch(Match match);
	JoinMatch findByPlayerAndMatchBefore(Player player, Match match);
}
