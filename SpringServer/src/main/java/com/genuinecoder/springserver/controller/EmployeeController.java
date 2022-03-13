package com.genuinecoder.springserver.controller;

import com.genuinecoder.springserver.model.employee.Employee;
import com.genuinecoder.springserver.model.employee.EmployeeDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  @Autowired
  private EmployeeDao employeeDao;

  @GetMapping("/employee/get-all")
  public List<Employee> getAllEmployees() {
    return employeeDao.getAllEmployees();
  }

  @PostMapping("/employee/save")
  public Employee save(@RequestBody Employee employee) {
    return employeeDao.save(employee);
  }
}
