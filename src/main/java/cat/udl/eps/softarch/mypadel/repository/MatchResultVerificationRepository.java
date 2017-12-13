package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.MatchResultVerification;
import cat.udl.eps.softarch.mypadel.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MatchResultVerificationRepository extends PagingAndSortingRepository<MatchResultVerification, Integer> {
	List<MatchResultVerification> findByPlayer(Player player);
}
