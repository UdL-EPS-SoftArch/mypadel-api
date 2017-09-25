
package cat.udl.eps.softarch.mypadel.repository;

import cat.udl.eps.softarch.mypadel.domain.*;
import org.springframework.data.repository.*;
import org.springframework.data.rest.core.annotation.*;

@RepositoryRestResource
public interface CourtRepository extends PagingAndSortingRepository<Court, Integer> {

}

