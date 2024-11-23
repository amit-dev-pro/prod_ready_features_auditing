package com.amit.prod_ready_features_auditing.clients;

import com.amit.prod_ready_features_auditing.advices.ApiResponse;
import com.amit.prod_ready_features_auditing.dto.EmployeeDto;
import com.amit.prod_ready_features_auditing.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@RequiredArgsConstructor
class EmployeeClientImpl implements EmployeeClient{

    private final RestClient restClient;
    Logger log=LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.trace("trying to retrieve all employees in getAllEmployees");

        try{
            log.info("Attempting to all call the restClient Method in getAllEmployees ");
            ApiResponse<List<EmployeeDto>> employeeDtoList=restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res)->{
                        log.error(new String(res.getBody().readAllBytes()));
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {}, {}, {} ",employeeDtoList.getData(),"Hello",5);
            return employeeDtoList.getData();

        }catch(Exception e){
            log.error("Exception occurred in getAllEmployees ",e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        log.trace("Trying to get Employee By Id in getEmployeeById with id: {}", employeeId);
        try {
            ApiResponse<EmployeeDto> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {
        log.trace("Trying to create Employee with information {}", employeeDto);
        try {
            ResponseEntity<ApiResponse<EmployeeDto>> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.debug("4xxClient error occurred during createNewEmployee");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.trace("Successfully created a new employee : {}", employeeDTOApiResponse.getBody());
            return employeeDTOApiResponse.getBody().getData();
        }
        catch (Exception e) {
            log.error("Exception occurred in createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }

}
