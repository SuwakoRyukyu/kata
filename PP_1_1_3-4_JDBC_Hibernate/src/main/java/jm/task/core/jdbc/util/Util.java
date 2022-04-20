package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

 /*
Алгоритм создания синглтона:
#1. – Нужно добавить в класс приватное статическое поле, содержащее одиночный объект:
#2. – Сделать конструктор класса приватным
#3. – Объявить статический создающий метод, который будет использоваться для получения одиночки:
Что здесь не так?
*/
public class Util {

    private Util() {}

    private static SessionFactory sf;
    // Singleton initialization of class Util
    public static SessionFactory getFactory() {
        if (sf == null) {
            Properties prop = new Properties();
            try {
                prop.setProperty("hibernate.connection.url", "jdbc:mariadb://localhost:3306/mydb");
                prop.setProperty("hibernate.connection.username", "root");
                prop.setProperty("hibernate.connection.password", "suwako");
                prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                prop.setProperty("hibernate.show_sql", "true");
                prop.setProperty("hibernate.format_sql", "true");
                sf = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
            } catch (HibernateException e) {
                System.out.println("Error while trying to initialize Util");
            }
        }
        return sf;
    }
}