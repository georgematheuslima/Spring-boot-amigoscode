package com.example.demo.student;

import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepositorty studentRepositorty;

    @Autowired
    public StudentService(StudentRepositorty studentRepositorty)
    {
        this.studentRepositorty = studentRepositorty;
    }


    public List<Student> getStudents() {return studentRepositorty.findAll();}


    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepositorty.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepositorty.save(student);

    }


    public void deleteStudent(Long studentId){
      boolean exists = studentRepositorty.existsById(studentId);
      if (!exists){
          throw new IllegalStateException("Student with id " + studentId + "does not exist");
      }
      studentRepositorty.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepositorty.findById(studentId).orElseThrow(()-> new IllegalStateException(
            "Student with id " + studentId + "does not exist."));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (null != email && email.length() >0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepositorty.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(email);
        }

    }

}
