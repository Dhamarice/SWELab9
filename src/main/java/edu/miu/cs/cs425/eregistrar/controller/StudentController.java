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
@RequestMapping(value="/eRegistrar/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

//    http://localhost/eregistrar/api/student/list - Displays JSON array of Students data.
//    http://localhost/eregistrar/api/student/register - Register a new Student into the system
//    http://localhost/eregistrar/api/student/get/1 - Read/display a Student data for student with ID, 1.
//    http://localhost/eregistrar/api/student/delete/3 - Delete the student data for student with ID, 3.

    @GetMapping(value={"/list"})
    public List<Student> listStudent(){
        return studentService.findAllStudents();
    }

    @GetMapping(value={"/{id}"})
    public Student student(@PathVariable Long id){
        return studentService.findStudent(id);
    }

    @PostMapping("/register")
    public Student RegisterStudent(@Valid @RequestBody Student student){
     return studentService.saveStudent(student);

    }

    @PostMapping(value={"/update"})
    public Student updateStudent(@RequestBody Student student){

        return studentService.saveStudent(student);
    }

    @GetMapping("/delete/{id}")
    public Student deleteStudent(@PathVariable Long id){
        Student student = studentService.findStudent(id);
        return studentService.deleteStudent(student);
    }

}
