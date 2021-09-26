package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.models.Student;
import cg.wbd.grandemonstration.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {
    @Autowired
    IStudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> showAll(@PageableDefault(size = 2) Pageable page){
        Page<Student> students = studentService.findAll(page);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> studentOptional = studentService.findOne(id);
        if (!studentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        student.setId(studentOptional.get().getId());
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        Optional<Student> studentOptional = studentService.findOne(id);
        if (!studentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
        public ResponseEntity<Iterable<Student>> search(@RequestParam("name") String name ){
            return new ResponseEntity<>(studentService.findAllByNameContaining(name), HttpStatus.OK);
        }


}
