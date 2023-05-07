import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {
    private final Connection connection;

    public EmployeeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createEmployee(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement
                ("INSERT INTO employee (first_name,last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        try (PreparedStatement statement = connection.prepareStatement
                ("SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id AND id=(?)")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List <Employee> employees = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee LEFT JOIN city ON employee.city_id=city.city_id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                employees.add((new Employee(id, firstName, lastName, gender, age)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
            }

    @Override
    public void updateEmployeeById(int id, int age) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET age=(?) WHERE id=(?)")) {

            statement.setInt(1, age);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteEmployeeById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE id=(?)")) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
