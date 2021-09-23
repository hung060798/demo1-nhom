package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentService {
    Page<Student> findAll(Pageable pageable);

    Optional<Student> findOne(Long id);

    void save(Student student);

    void delete(Long id);

}
