package com.ers.employeemgrserver.domain.employee.controllers;

import com.ers.employeemgrserver.domain.employee.models.Employee;
import com.ers.employeemgrserver.domain.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Allows Spring to look in this class for URI or URL to access external request
@RequestMapping("/api/v1/employees") // This is the Base Route for our external request for anything coming into the server
                                    // v1 allows us to create different controllers for each version if we want to upgrade or change our api
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired // Tells the spring container to look for object that implements employeeService and inject it into this controller
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll(){
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(employees, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        employee = employeeService.create(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);

    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
        Employee employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);

        }

     @GetMapping("lookup")
    public ResponseEntity<Employee> getByEmail(@RequestParam String email) {
        Employee employee = employeeService.getByEmail(email);
        return new ResponseEntity<>(employee, HttpStatus.OK);
     }
     @PutMapping("{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employeeDetail){
        employeeDetail = employeeService.update(id, employeeDetail);
        return new ResponseEntity<>(employeeDetail, HttpStatus.ACCEPTED);
     }

     @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
    }


