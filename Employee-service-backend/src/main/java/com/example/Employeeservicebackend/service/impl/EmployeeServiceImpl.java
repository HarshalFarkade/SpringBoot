package com.example.Employeeservicebackend.service.impl;

import com.example.Employeeservicebackend.dto.EmployeeDto;
import com.example.Employeeservicebackend.entity.Employee;
import com.example.Employeeservicebackend.exception.ResourceNotFoundException;
import com.example.Employeeservicebackend.mapper.EmployeeMapper;
import com.example.Employeeservicebackend.repository.EmployeeRepository;
import com.example.Employeeservicebackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee= employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Integer id) {
        try {
            Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Employee with this id is not found:" + id));
            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
            return employeeDto;
        } catch (ResourceNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos =employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
        return employeeDtos;
    }

    @Override
    public EmployeeDto updateEmployeeById(Integer id, EmployeeDto employeeDto) throws ResourceNotFoundException{
        try {

            Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Employee with this id is not found:"+id)
            );
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setEmail(employeeDto.getEmail());
            existingEmployee.setPassword(employeeDto.getPassword());
            existingEmployee.setAge(employeeDto.getAge());
            existingEmployee.setDateOfBirth(employeeDto.getDateOfBirth());
            existingEmployee.setGender(employeeDto.getGender());

            employeeRepository.save(existingEmployee);

            return EmployeeMapper.mapToEmployeeDto(existingEmployee);

        }catch (ResourceNotFoundException ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public void deleteEmployeeById(Integer id) throws ResourceNotFoundException{
        try {
            Employee employee= employeeRepository.findById(id).orElseThrow(
                    ()-> new ResourceNotFoundException("Employee with this id is not found:"+id)
            );
            employeeRepository.deleteById(id);
        }catch (ResourceNotFoundException ex){
            ex.printStackTrace();
            throw ex;
        }
    }
}
