package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.models.Classes;
import cg.wbd.grandemonstration.repository.IClassesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ClassesService implements IClassesService{
    @Autowired
    IClassesRepo classRepo;


    @Override
    public Iterable<Classes> findAll() {
        return  classRepo.findAll();
    }

    @Override
    public Optional<Classes> findOne(Long id) {
        return classRepo.findById(id);
    }

    @Override
    public void  save(Classes classes) {
          classRepo.save(classes);
    }

    @Override
    public void delete(Long id) {
        classRepo.deleteById(id);
    }
}
