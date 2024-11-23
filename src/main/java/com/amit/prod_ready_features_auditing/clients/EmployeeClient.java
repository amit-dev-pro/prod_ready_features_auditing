package com.amit.prod_ready_features_auditing.clients;

import com.amit.prod_ready_features_auditing.dto.EmployeeDto;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


public interface EmployeeClient {

    List<EmployeeDto> getAllEmployees();
    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto createNewEmployee(EmployeeDto employeeDto);
}
