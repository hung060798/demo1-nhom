package cg.wbd.grandemonstration.repository;

import cg.wbd.grandemonstration.models.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepo extends PagingAndSortingRepository<Student, Long> {
    Iterable<Student> findAllByNameContaining(String nameStudent);
}
