package com.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.models.entities.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
    Optional<Student> findByCpf(String cpf);
    Optional<Student> findByPhoneNumber(String phoneNumber);

}
