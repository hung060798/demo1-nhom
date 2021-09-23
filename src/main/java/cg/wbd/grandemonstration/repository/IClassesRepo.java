package cg.wbd.grandemonstration.repository;

import cg.wbd.grandemonstration.models.Classes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassesRepo extends PagingAndSortingRepository<Classes, Long>{
}
