import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        CityDAO cityDAO = new CityDaoImpl();
        City city1 = new City("Orenburg");
        cityDAO.createCity(city1);

        City cityForEmployee = cityDAO.getCityById(1);
        Employee employee = new Employee("Anna", "Andreeva", "Woman", 45);
        employee.setCity(cityForEmployee);
        employeeDAO.createEmployee(employee);
        cityDAO.deleteCity(cityForEmployee);

        }
    }
