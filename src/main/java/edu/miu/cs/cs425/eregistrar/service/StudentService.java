package edu.miu.cs.cs425.eregistrar.service;

import edu.miu.cs.cs425.eregistrar.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> findAllStudents();
    Student findStudent(Long id);
    Student saveStudent(Student student);
    Student deleteStudent(Student student);
}
