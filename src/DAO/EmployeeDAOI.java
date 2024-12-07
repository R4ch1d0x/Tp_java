package DAO;

import Model.Employee;

import java.util.*;

public interface EmployeeDAOI {

    Employee findById(int employeId);
    List<Employee> findAll();
    void add(Employee E);
    void update(Employee E,int id);
    void delete(int id);
    List<Employee.Role>findAllRoles();
    List<Employee.Poste>findAllPostes();


}