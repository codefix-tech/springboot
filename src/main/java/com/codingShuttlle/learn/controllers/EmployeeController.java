package com.codingShuttlle.learn.controllers;

import com.codingShuttlle.learn.dto.EmployeeDTO;
import com.codingShuttlle.learn.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> employeeById(@PathVariable(name = "employeeId") Long Id){
      Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(Id);

        return employeeDTO
                .map(EmployeeDTO1 -> ResponseEntity.ok(EmployeeDTO1))
                .orElse(ResponseEntity.notFound().build());

    }

   @GetMapping
    public ResponseEntity <List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                @RequestParam(required = false) String sortBy){

        return ResponseEntity.ok(employeeService.getAlLEmployees());

    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO  inputEmployee){
       EmployeeDTO saveEmployee = employeeService.createNewEmployee(inputEmployee);
       return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO , @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
      boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
      if(gotDeleted) return ResponseEntity.ok(true);
      return ResponseEntity.notFound().build();

    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,
                                                 @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updatePartialEmployeeById(employeeId,updates));

    }

}
