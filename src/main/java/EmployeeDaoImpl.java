import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    @Override
    public void createEmployee(Employee employee) {
        try (
                Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try (
                Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (
                Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(" FROM Employee").list();
        }
    }

    @Override
    public void updateEmployeeById(Employee employee) {
        try (
                Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }


    @Override
    public void deleteEmployeeById(Employee employee) {
        try (
                Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
            }
        }
    }
