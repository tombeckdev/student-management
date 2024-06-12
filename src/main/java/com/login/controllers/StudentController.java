package com.login.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.models.dto.StudentDto;
import com.login.models.entities.Student;
import com.login.services.imp.StudentServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentServiceImp studentService;

    @Autowired
    public StudentController(StudentServiceImp studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        return ResponseEntity.ok().body(new StudentDto(student));
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll(){
        return ResponseEntity.ok().body(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody @Valid StudentDto studentDto) {
        return new ResponseEntity<StudentDto>(studentService.save(studentDto), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable @Valid Long id, @RequestBody StudentDto studentDto) {
        StudentDto student = studentService.update(id, studentDto);
        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }


}
