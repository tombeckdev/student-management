package com.login.services;

import java.util.List;

import com.login.models.dto.StudentDto;
import com.login.models.entities.Student;

public interface StudentService {

    Student findById(Long id);
    List<StudentDto> findAll();
    StudentDto save(StudentDto studentDto);
    StudentDto update(Long id, StudentDto studentDto);
    void delete(Long id);

}
