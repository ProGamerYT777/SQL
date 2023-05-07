import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "1";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = (?)")) {
            statement.setInt(1, 6);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String first_name = "First_name: " + resultSet.getString("first_name");
                String last_name = "Fast_name: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                int age = resultSet.getInt(5);
                System.out.println(first_name);
                System.out.println(last_name);
                System.out.println(gender);
                System.out.println(age);
            }
            EmployeeDAO employeeDao = new EmployeeDaoImpl(connection);
            Employee empl1 = new Employee(6, "Anna", "Ivanova", "Woman", 35);
            employeeDao.createEmployee(empl1);
            List<Employee> employeeList = new ArrayList<>(employeeDao.getAllEmployees());
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
            employeeDao.updateEmployeeById(3, 25);
            employeeDao.deleteEmployeeById(3);
        }
    }
}
