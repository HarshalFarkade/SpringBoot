package com.example.Employeeservicebackend.mapper;

import com.example.Employeeservicebackend.dto.EmployeeDto;
import com.example.Employeeservicebackend.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
        employee.getId(),
        employee.getFirstName(),
        employee.getLastName(),
        employee.getEmail(),
        employee.getPassword(),
        employee.getAge(),
        employee.getDateOfBirth(),
        employee.getGender()
        );

    }

    public static Employee mapToEmployee(EmployeeDto  employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getPassword(),
                employeeDto.getAge(),
                employeeDto.getDateOfBirth(),
                employeeDto.getGender()
        );
    }


}
