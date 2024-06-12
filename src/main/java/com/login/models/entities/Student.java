package com.login.models.entities;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.login.models.dto.StudentDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @CPF(message = "Enter a valid CPF")
    @NotNull
    @NotBlank
    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDate birthDate;

    @Email(message = "Enter a valid email address")
    @NotBlank
    @NotNull
    private String email;

    private String phoneNumber;

    public Student(StudentDto studentDto){
        this.id = studentDto.getId();
        this.name = studentDto.getName();
        this.cpf = studentDto.getCpf();
        this.birthDate = studentDto.getBirthDate();
        this.email = studentDto.getEmail();
        this.phoneNumber = studentDto.getPhoneNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(cpf, student.cpf) && Objects.equals(birthDate, student.birthDate) && Objects.equals(email, student.email) && Objects.equals(phoneNumber, student.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, birthDate, email, phoneNumber);
    }
}
