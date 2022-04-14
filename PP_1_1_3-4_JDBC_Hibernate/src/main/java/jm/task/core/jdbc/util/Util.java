package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

// The reason I'm using Mariadb is that the default implementation of MySQL on my Linux distro (Arch Linux) is Mariadb, and there's no actual way to change it to MySQL as far as I can see.
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