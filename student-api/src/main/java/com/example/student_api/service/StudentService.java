package com.example.student_api.service;

import com.example.student_api.entity.Student;
import com.example.student_api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public List<Student> getStudentsByCourse(String course) {
        return repository.findByCourse(course);
    }

    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    public Optional<Student> updateStudent(Long id, Student newStudent) {
        Optional<Student> optionalStudent = repository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            student.setName(newStudent.getName());
            student.setEmail(newStudent.getEmail());
            student.setCourse(newStudent.getCourse());
            student.setAge(newStudent.getAge());

            return Optional.of(repository.save(student));
        }

        return Optional.empty();
    }

    public boolean deleteStudent(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}