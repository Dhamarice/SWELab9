package edu.miu.cs.cs425.eregistrar.repository;

import edu.miu.cs.cs425.eregistrar.model.Student;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
List<Student> findAllByFirstNameContainsOrLastNameContainsOrMiddleNameContainsOrCgpaContainsOrStudentNumberContains(String searchString,String searchString1,String searchString2,String searchString3, String searchString4);
List<Student> findAllByEnrollmentDateContains(LocalDate searchString);
}
