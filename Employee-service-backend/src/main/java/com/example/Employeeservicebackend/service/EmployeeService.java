package com.example.Employeeservicebackend.service;

import com.example.Employeeservicebackend.dto.EmployeeDto;
import com.example.Employeeservicebackend.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    EmployeeDto createNewEmployee(EmployeeDto employee);

    EmployeeDto getEmployeeById(Integer id);

    List<EmployeeDto>  getAllEmployee();

    EmployeeDto updateEmployeeById(Integer id ,EmployeeDto employeeDto);

    void deleteEmployeeById(Integer id);
}
