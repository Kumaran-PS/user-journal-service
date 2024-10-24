package com.example.user_management_microservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Generated;
import org.springframework.context.annotation.Primary;

import java.util.Set;

@Entity
@Data
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;


    private long phoneNumber;

    private String password;

    @NotBlank
    private String fullName;

    @Email
    private String email;

    private String userRole;


}
