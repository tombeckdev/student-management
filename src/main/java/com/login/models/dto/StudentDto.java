package com.login.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.login.models.entities.Student;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Long id;
    private String name;
    private String cpf;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;
    private String email;
    @Column(updatable = false)
    private String phoneNumber;


    public StudentDto(Student student){
        this.id = student.getId();
        this.name = student.getName();
        this.cpf = student.getCpf();
        this.birthDate = student.getBirthDate();
        this.email = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto that = (StudentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cpf, that.cpf) && Objects.equals(birthDate, that.birthDate) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, birthDate, email, phoneNumber);
    }
}
