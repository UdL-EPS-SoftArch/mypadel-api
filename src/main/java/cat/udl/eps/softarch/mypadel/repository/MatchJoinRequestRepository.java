package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.MatchJoinRequest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MatchJoinRequestRepository extends PagingAndSortingRepository<MatchJoinRequest, Long>{
}
