import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {

            EmployeeDAO employeeDao = new EmployeeDaoImpl();
            Employee employee1 = new Employee(6, "Anna", "Ivanova", "Woman", 35, 2);
            employeeDao.createEmployee(employee1);
            System.out.println(employeeDao.getEmployeeById(4));
            List<Employee> employeeList = new ArrayList<>(employeeDao.getAllEmployees());
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }

            Employee employee2 = new Employee(9,"Anton","Ivanov","Man",30, 5);
            employeeDao.updateEmployeeById(employee2);
            employeeDao.deleteEmployeeById(employee2);
        }
    }
