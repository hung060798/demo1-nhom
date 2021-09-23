package cg.wbd.grandemonstration.service;

import cg.wbd.grandemonstration.models.Classes;

import java.util.List;
import java.util.Optional;

public interface IClassesService {

    Iterable<Classes> findAll();

    Optional<Classes> findOne(Long id);

    void  save(Classes classes);

    void delete(Long id);
}
