package com.login.services.imp;

import com.login.exceptions.ContentNotFoundException;
import com.login.exceptions.DataIntegrityViolationException;
import com.login.models.dto.StudentDto;
import com.login.models.entities.Student;
import com.login.repositories.StudentRepository;
import com.login.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findById(Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElseThrow( (() -> new ContentNotFoundException("Student id " + id + " not found")));
    }

    @Override
    public List<StudentDto> findAll(){
        return studentRepository.findAll().stream().map(StudentDto::new).toList();
    }

    @Override
    public StudentDto save(@Valid StudentDto studentDto) {
        validateInfo(studentDto);
        studentRepository.save(new Student(studentDto));
        return studentDto;
    }

    @Override
    public StudentDto update(Long id, @Valid StudentDto studentDto){
        studentDto.setId(id);
        Student oldStudent = findById(id);
        oldStudent = new Student(studentDto);
        return new StudentDto(studentRepository.save(oldStudent));
    }

    @Override
    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    private void validateInfo(StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findByCpf(studentDto.getCpf());
        if(optionalStudent.isPresent() && optionalStudent.get().getId() != (studentDto.getId())) {
            throw new DataIntegrityViolationException("This CPF is already registered in our system");
        }
        optionalStudent = studentRepository.findByEmail(studentDto.getEmail());
        if(optionalStudent.isPresent() && optionalStudent.get().getId() != studentDto.getId()) {
            throw new DataIntegrityViolationException("This email is already registered in our system");
        }
        optionalStudent = studentRepository.findByPhoneNumber(studentDto.getPhoneNumber());
        if(optionalStudent.isPresent() && optionalStudent.get().getId() != studentDto.getId()) {
            throw new DataIntegrityViolationException("This phone number is already registered in our system");
        }
    }

//    public int generateRegistrationNumber(int uniqueNumber){
//        Random random = new Random();
//        int number;
//        do {
//            number = 1000000000 + random.nextInt(900000000);
//        }
//        while(studentRepository.existsByRegistrationNumber(number));
//        return number;
//    }

}
