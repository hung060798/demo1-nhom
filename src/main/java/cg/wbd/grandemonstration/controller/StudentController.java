package cg.wbd.grandemonstration.controller;

import cg.wbd.grandemonstration.models.Classes;
import cg.wbd.grandemonstration.models.Student;
import cg.wbd.grandemonstration.service.IClassesService;
import cg.wbd.grandemonstration.service.IStudentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@RequestMapping("/students")
@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassesService classesService;

    @ModelAttribute("classzz")
    public Iterable<Classes> classes() {
        return classesService.findAll();
    }

    @GetMapping("/list")
    public ModelAndView showStudent(@PageableDefault(size = 2) Pageable page) {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        Page<Student> list = studentService.findAll(page);
        modelAndView.addObject("listStudent", list);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @Value("${path}")
    private String pathImg;

    @PostMapping("/create-student")
    public ModelAndView create(@Valid @ModelAttribute("student") Student student, @RequestParam MultipartFile image, BindingResult bindingResult) {
        String fileName = image.getOriginalFilename();
        student.setImg(fileName);
        try {
            FileCopyUtils.copy(image.getBytes(), new File(pathImg + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:/student/create");
        }
        studentService.save(student);
        return new ModelAndView("redirect:/students/list");
    }

    @GetMapping("/edit/{id}")
        public ModelAndView showEdit(@PathVariable Long id) {
            ModelAndView modelAndView = new ModelAndView("student/edit");
            modelAndView.addObject("student", studentService.findOne(id));
            return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@ModelAttribute("student") Student student, @RequestParam MultipartFile image) {
        String fileName = image.getOriginalFilename();
        student.setImg(fileName);
        try {
            FileCopyUtils.copy(image.getBytes(), new File(pathImg + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentService.save(student);
        return new ModelAndView("redirect:/students/list");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        studentService.delete(id);
        return new ModelAndView("redirect:/students/list");
    }
}
