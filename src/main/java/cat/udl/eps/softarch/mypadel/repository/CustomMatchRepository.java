package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.CustomMatch;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.PagingAndSortingRepository;


@RepositoryRestResource
public interface CustomMatchRepository extends PagingAndSortingRepository<CustomMatch, Long> {
}
