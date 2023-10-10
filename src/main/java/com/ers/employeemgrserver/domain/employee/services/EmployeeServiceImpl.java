package com.ers.employeemgrserver.domain.employee.services;

import com.ers.employeemgrserver.domain.core.exceptions.ResourceCreationException;
import com.ers.employeemgrserver.domain.core.exceptions.ResourceNotFoundException;
import com.ers.employeemgrserver.domain.employee.models.Employee;
import com.ers.employeemgrserver.domain.employee.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Allows Spring to create this method
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired //Will tell the Spring container to grab the employeeRepo or make one.
    public EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee create(Employee employee) throws ResourceCreationException {
        Optional<Employee> optional = employeeRepository.findByEmail(employee.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Employee with email exists: " + employee.getEmail());
        employee = employeeRepository.save(employee);
        return employee; // This will give an employee with an id.
    }

    @Override
    public Employee getById(Long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No Employee with id: " + id));
        return employee;
    }

    @Override
    public Employee getByEmail(String email) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No Employee with email: " + email));
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Long id, Employee employeeDetail) throws ResourceNotFoundException {
        Employee employee = getById(id);
        employee.setFirstName((employeeDetail.getFirstName()));
        employee.setLastName(employeeDetail.getLastName());
        employee.setEmail(employeeDetail.getEmail());
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(Long id) {
        Employee employee = getById(id);
        employeeRepository.delete(employee);

    }
}
