package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.models.Student;
import cg.wbd.grandemonstration.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentService implements IStudentService{

    @Autowired
    IStudentRepo studentRepo;
    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    @Override
    public Optional<Student> findOne(Long id) {
        return studentRepo.findById(id);
    }

    @Override
    public void save(Student student) {
          studentRepo.save(student);
    }

    @Override
    public void delete(Long id ) {
        studentRepo.deleteById(id);
    }
}
