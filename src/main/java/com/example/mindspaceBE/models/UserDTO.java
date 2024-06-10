package com.example.mindspaceBE.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class UserDTO {
    private String fullName;
    private String gender;
    private Integer age;
    private String email;
    private String password;

}