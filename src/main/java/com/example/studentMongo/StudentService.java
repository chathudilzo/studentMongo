package com.example.studentMongo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }


    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByEmail(String email){
        return studentRepository.findStudentByEmail(email);
    }

    public Optional<Student> getStudentById(String id){
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public boolean deleteStudent(String id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> findByNameOrEmail(String keyword){
        return studentRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword,keyword);
    }


    public Optional<Student>updateStudent(String id,Student updateStudent){
        return studentRepository.findById(id).map(student->{
            student.setName(updateStudent.getName());
            student.setEmail(updateStudent.getEmail());
            student.setAge(updateStudent.getAge());
            return studentRepository.save(student);
        });
    }
}
