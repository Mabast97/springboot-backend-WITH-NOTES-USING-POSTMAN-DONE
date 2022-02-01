package net.javaguides.springbootbackend.controller;

import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// We should implement Service layer, because Control layer is depends on Service layer.

@RestController
/* Instead of (@RestController) we can use ( @Controller ). But if we use it, we should use (@ResponseBody) on top of each
restApi that we defined inside this controller.

So, @RestController is a  convenient annotation that combines @Controller and @ResponseBody, which eliminates the need to
annotate every request handling method of the controller class with the @ResponseBody annotation.
 */
@RequestMapping("/api/employees")
public class EmployeeController {
    // Labar away tanha yak class implement y EmployeeService y krdya, labar awaya ka pewest ba @Autowired u tartibatakan nakatn.
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Build create employee REST API.
    // We use ResponseEntity class, because we can provide complete response details in this class.
    // by using it, we can construct the response of the rest api
    @PostMapping()  // You don't need to specify the url here, because in @RequestMapping("/api/employees") you specified it.
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        // @RequestBody allows us to retrieve the request's body and automatically convert the JSON to java Object
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // Build get all employees REST API.
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Build get employee by id REST API
    @GetMapping({"id"})  // for example: http://localhost:8086/api/employees/2
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    // Build update employee REST API
    @PutMapping("{id}") // for example: http://localhost:8086/api/employees/2
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,  @RequestBody Employee employee) {
        // we should store the updated employee rest api request body in some object. So, that is why we use @RequestBody and also for
        // converting the JSON to the java object.
        return  new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    // Build delete employee REST API
    @DeleteMapping("{id}") // for example:  http://localhost:8086/api/employees/3
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        // Delete employee from the DB
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }

}
