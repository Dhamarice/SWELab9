package edu.miu.cs.cs425.eregistrar.controller;

import edu.miu.cs.cs425.eregistrar.model.Student;
import edu.miu.cs.cs425.eregistrar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        modelAndView.setViewName("index");
        return modelAndView;

    }


//    @GetMapping("/student/list")
//    public String showStudentList(Model model){
//        model.addAttribute("students", studentService.findAllStudents());
//        return "studentlist";
//    }

    @GetMapping("/student/list")
    public ModelAndView showStudentList(Model model){
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("students", studentService.findAllStudents());
        modelAndView.setViewName("/student/list");
        return modelAndView;
    }

@GetMapping("/student/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Student student = studentService.findStudent(id);
        model.addAttribute("student", student);
        return "update-student";
}

@PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result, Model model){

        if(result.hasErrors()){
            student.setStudentId(id);
            return "update-student";
        }
        studentService.saveStudent(student);
        return "redirect:/index";
}

@GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model){
        Student student = studentService.findStudent(id);

        studentService.deleteStudent(student);
        return "redirect:/index";
}


}
