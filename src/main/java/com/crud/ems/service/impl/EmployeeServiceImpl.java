package com.crud.ems.service.impl;

import com.crud.ems.dto.EmployeeDto;
import com.crud.ems.entity.Employee;
import com.crud.ems.exceptions.ResourceNotFoundException;
import com.crud.ems.mapper.EmployeeMapper;
import com.crud.ems.repository.EmployeeRepository;
import com.crud.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with employee id"+employeeId));


        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployeeDetails(Long employeeId, EmployeeDto updateEmployeeDetails) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee doesn't exist with employee id"+employeeId)
        );
        employee.setFirstName(updateEmployeeDetails.getFirstName());
        employee.setLastName(updateEmployeeDetails.getLastName());
        employee.setEmail(updateEmployeeDetails.getEmail());
        Employee updatedEmployeeDetails = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeDetails);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee doesn't exist with employee id"+employeeId)
        );
        employeeRepository.deleteById(employeeId);


    }
}
