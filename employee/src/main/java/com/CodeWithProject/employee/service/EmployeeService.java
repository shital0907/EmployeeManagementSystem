package com.CodeWithProject.employee.service;

import com.CodeWithProject.employee.entity.Employee;
import com.CodeWithProject.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

private final EmployeeRepository employeeRepository;

public Employee postEmployee(Employee employee) {
    return employeeRepository.save(employee);
}

 public List<Employee> getAllEmployee(){
     return employeeRepository.findAll();
    }
    public void deleteEmployee(Long id){
    if(!employeeRepository.existsById(id)){
        throw new EntityNotFoundException("Employee with id " + id + "not found");
    }
    employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id){
    return employeeRepository.findById(id).orElse(null);
}

public Employee updateEmployee(Long id, Employee employee){
    Optional<Employee> OptionalEmployee = employeeRepository.findById(id);

    if(OptionalEmployee.isPresent()){
        Employee existingEmployee = OptionalEmployee.get();

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setDepartment(employee.getDepartment());

        return employeeRepository.save(existingEmployee);

    }
    return null;
}
}
