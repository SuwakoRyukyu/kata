package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

   private final SessionFactory sf = Util.getInstance().getFactory();

    @Override
    public void createUsersTable() {
        Transaction myTransaction = null;
        try (Session sess = sf.openSession()) {
            myTransaction = sess.beginTransaction();
            sess.createSQLQuery(
                            "CREATE TABLE IF NOT EXISTS Users(" +
                                    "id INT NOT NULL AUTO_INCREMENT," +
                                    "firstName VARCHAR(45) NOT NULL," +
                                    "lastName VARCHAR (45) NOT NULL," +
                                    "age INT NOT NULL," +
                                   "PRIMARY KEY (id))")
                    .addEntity(User.class)
                    .executeUpdate();
            myTransaction.commit();
        } catch (HibernateException e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction myTransaction = null;
        try (Session sess = sf.openSession()) {
            myTransaction = sess.beginTransaction();
            sess.createSQLQuery("DROP TABLE IF EXISTS Users").executeUpdate();
            sess.getTransaction().commit();
        } catch (HibernateException e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction myTransaction = null;
        try (Session sess = sf.openSession()) {
            User myUser = new User(name, lastName, age);
            myTransaction = sess.beginTransaction();
            sess.persist("User", myUser);
            sess.getTransaction().commit();
        } catch (HibernateException e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction myTransaction = null;
        try (Session sess = sf.openSession()){
            myTransaction = sess.beginTransaction();
            Query myQuery = sess.createQuery("delete User where id = :id");
            myQuery.setParameter("id", id);
            myQuery.executeUpdate();
            myTransaction.commit();
        } catch (HibernateException e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction myTransaction = null;
        List<User> userList = null;
        try (Session sess = sf.openSession()){
            myTransaction = sess.beginTransaction();
            userList = sess.createQuery("from User", User.class).getResultList();
            myTransaction.commit();
        } catch (HibernateException e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction myTransaction = null;
        try (Session sess = sf.openSession()){
            myTransaction = sess.beginTransaction();
            sess.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            myTransaction.commit();
        } catch(HibernateException e) {
            assert myTransaction != null;
            myTransaction.rollback();
            e.printStackTrace();
        }
    }
}