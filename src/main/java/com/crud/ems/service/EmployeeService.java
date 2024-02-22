package com.crud.ems.service;

import com.crud.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployeeDetails(Long employeeId,EmployeeDto updateEmployeeDetails);
    void deleteEmployee(Long employeeId);

}
