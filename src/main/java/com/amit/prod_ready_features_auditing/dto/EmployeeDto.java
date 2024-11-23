package com.amit.prod_ready_features_auditing.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role; //ADMIN,USER
    private Double salary;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
