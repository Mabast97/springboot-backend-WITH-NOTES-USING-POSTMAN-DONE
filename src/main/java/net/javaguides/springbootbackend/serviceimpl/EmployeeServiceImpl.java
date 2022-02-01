package net.javaguides.springbootbackend.serviceimpl;

import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import net.javaguides.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// We should implement Service layer, because Control layer is depends on Service layer.

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Constructor-based Injection
    /* @Autowired  // Here, we don't need to write this annotation here. Because whenever a spring file(spring bean) has only one
    constructor, the @Autowired annotation can be omitted and spring will use
    that constructor and inject all necessary dependencies.
    And also, you can add it too.
     */
    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return  employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll() ;
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()) {
//            return employee.get();
//        }else {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
        // Or you can write it by the bellow way :

        return employeeRepository.findById(id).orElseThrow( () ->
                new ResourceNotFoundException("Employee", "Id", id) );
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        // we need to check whether the employee with the given id is existing int the Database or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id) );

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        // Check whether an employee exist in the DB or not
        employeeRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Employee", "id", id) );

        employeeRepository.deleteById(id);
    }
}
