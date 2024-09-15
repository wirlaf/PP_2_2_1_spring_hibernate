package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        String hql = "from User";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    public User findUserByCar(Car car) {

        String hql = "SELECT u FROM User u JOIN FETCH u.car c WHERE c.id = :carId";

        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("carId", car.getId())
                .uniqueResult();
    }
}
