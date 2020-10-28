package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getHibernateConnect().openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE `user` (`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,`name` VARCHAR(255) NOT NULL, `last_name` VARCHAR(255) NOT NULL, `age` INTEGER (255) NOT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getHibernateConnect().openSession();
        session.beginTransaction();
        String sql = "drop table IF EXISTS user";
        session.createNativeQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getHibernateConnect().openSession();
        session.beginTransaction();
        User user = new User(name,lastName,age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getHibernateConnect().openSession();
        session.beginTransaction();
        String hql = "delete User where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id",id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getHibernateConnect().openSession();
        session.beginTransaction();
        String hql = "FROM User";
        Query query = session.createQuery(hql);
        List results = query.list();
        session.getTransaction().commit();
        session.close();
        return results;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getHibernateConnect().openSession();
        session.beginTransaction();
        String hql = "delete User";
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
