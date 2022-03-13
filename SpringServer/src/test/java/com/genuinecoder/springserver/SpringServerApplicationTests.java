package com.genuinecoder.springserver;

import com.genuinecoder.springserver.model.employee.Employee;
import com.genuinecoder.springserver.model.employee.EmployeeDao;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class SpringServerApplicationTests {

  @Autowired
  private EmployeeDao employeeDao;

  @BeforeAll
  public void clear() {
    List<Employee> employees = employeeDao.getAllEmployees();
    for (Employee employee : employees) {
      employeeDao.delete(employee.getId());
    }
  }

  @Test
  void addEmployeeTest() {
    addEmployee("Bruce Wayne", "Building-X", "Security");
    addEmployee("Harvey Dent", "Building-2", "Police");
    addEmployee("Rachel", "Building-11", "IT");
  }

  private void addEmployee(String name, String location, String branch) {
    Employee employee = new Employee();
    employee.setName(name);
    employee.setLocation(location);
    employee.setBranch(branch);
    employeeDao.save(employee);
  }

}
