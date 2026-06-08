package com.codingShuttlle.learn.controllers;

import com.codingShuttlle.learn.dto.EmployeeDTO;
import com.codingShuttlle.learn.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/myname")
//    public String getMyName(){
//        return "My Name is Nishant Kumar";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO employeeById(@PathVariable(name = "employeeId") Long Id){

        return employeeService.getEmployeeById(Id);

    }

   @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                @RequestParam(required = false) String sortBy){

        return employeeService.getAlLEmployees();

    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO  inputEmployee){
       return employeeService.createNewEmployee(inputEmployee);

    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO , @PathVariable Long employeeId){
        return employeeService.updateEmployeeById(employeeId,employeeDTO);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId){
      return employeeService.deleteEmployeeById(employeeId);

    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String,Object> updates,
                                                 @PathVariable Long employeeId) {
        return employeeService.updatePartialEmployeeById(employeeId,updates);

    }

}
