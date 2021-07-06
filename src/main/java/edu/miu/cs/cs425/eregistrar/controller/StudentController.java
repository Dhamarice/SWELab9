package edu.miu.cs.cs425.eregistrar.controller;

import edu.miu.cs.cs425.eregistrar.model.Student;
import edu.miu.cs.cs425.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/")
    public ModelAndView testIndex(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @GetMapping("/student/new")
    public ModelAndView showRegisterForm(Student student){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/student/new");
        return modelAndView;
    }

    @PostMapping("/addstudent")
    public ModelAndView addStudent(@Valid Student student, BindingResult result, Model model){
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.setViewName("/student/new");
            return modelAndView;
        }
        studentService.saveStudent(student);
        modelAndView.addObject("students", studentService.findAllStudents());
        modelAndView.addObject("studentCount",studentService.countStudent());
        modelAndView.setViewName("student/list");
        return modelAndView;

    }


    @GetMapping("/student/list")
    public ModelAndView showStudentList(Model model){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("students", studentService.findAllStudents());
        modelAndView.addObject("studentCount",studentService.countStudent());
        modelAndView.setViewName("/student/list");
        return modelAndView;
    }

@GetMapping("/student/edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") long id){
        Student student = studentService.findStudent(id);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/student/edit");
    modelAndView.addObject("student",student);
        return modelAndView;
}

@PostMapping("/update")
    public ModelAndView updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult result){
    ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.setViewName("/student/edit");
            modelAndView.addObject("student", student);
            return modelAndView;
        }
        studentService.saveStudent(student);
    modelAndView.addObject("students", studentService.findAllStudents());
    modelAndView.addObject("studentCount",studentService.countStudent());
    modelAndView.setViewName("/student/list");
    return modelAndView;
}

@GetMapping("student/delete/{id}")
    public ModelAndView deleteStudent(@PathVariable("id") long id){
        Student student = studentService.findStudent(id);
        studentService.deleteStudent(student);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("students", studentService.findAllStudents());
    modelAndView.addObject("studentCount",studentService.countStudent());
    modelAndView.setViewName("/student/list");
    return modelAndView;
}

//student/search?searchString=ruv

    @GetMapping(value = {"/student/search", "/student/search"})
    public ModelAndView searchStudents(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> students = studentService.searchStudent(searchString);
        modelAndView.addObject("students", students);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("studentCount", students.size());
        modelAndView.setViewName("student/list");
        return modelAndView;
    }

}
