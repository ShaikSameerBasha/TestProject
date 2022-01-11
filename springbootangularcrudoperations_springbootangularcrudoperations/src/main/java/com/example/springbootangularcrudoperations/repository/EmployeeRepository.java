package com.example.springbootangularcrudoperations.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootangularcrudoperations.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
