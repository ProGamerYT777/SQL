import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDaoImpl implements CityDAO {
    @Override
    public void createCity(City city) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City getCityById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> getAllCities() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery(" FROM City").list();
        }
    }

    @Override
    public void updateCity(City city) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void deleteCity(City city) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }
}
