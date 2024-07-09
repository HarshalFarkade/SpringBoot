package com.example.Employeeservicebackend.controller;

import com.example.Employeeservicebackend.dto.EmployeeDto;
import com.example.Employeeservicebackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService ;

    /* Add Employee method*/

    @PostMapping("/addEmployee")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto  employee){

        EmployeeDto employeeDto  =employeeService.createNewEmployee(employee);

        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    };


    /* Get Employee By id*/

    @GetMapping("/employeeById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable ("id")Integer id){

        EmployeeDto employee=employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    /* get All Employee*/


    @GetMapping("/getAllEmployee")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDto = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeDto);
    }


    @PutMapping("/updateEmployee/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EmployeeDto> updateEmployeeById(
            @PathVariable ("id") Integer id ,@RequestBody EmployeeDto employeeDto){

        EmployeeDto employee =employeeService.updateEmployeeById(id, employeeDto);
        return ResponseEntity.ok(employee);

    }


    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteEmployeeById(@PathVariable ("id") Integer id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee Delete Successfully");
    }

}
