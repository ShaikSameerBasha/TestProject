package com.example.springbootangularcrudoperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootangularcrudoperations.exception.ResourceNotFoundException;
import com.example.springbootangularcrudoperations.model.Employee;
import com.example.springbootangularcrudoperations.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
public List<Employee> getEmployees()
{
		return employeeRepository.findAll();
}
	@GetMapping("/employees/{id}")
	public  ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId)throws ResourceNotFoundException
	{
		Employee employee=employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("employees not found"));
		return ResponseEntity.ok().body(employee);
	}
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
	return employeeRepository.save(employee);	
	}
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long EmployeeId, @RequestBody Employee employeeDetails)throws ResourceNotFoundException
	{
		Employee employee =employeeRepository.findById(EmployeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found for this id"));
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updateEmployee=employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	@DeleteMapping("/employees/{id}")  
	private void deleteBook(@PathVariable("id") int id)   
	{  
	employeeRepository.deleteById((long) id);  
	}  
	
}
