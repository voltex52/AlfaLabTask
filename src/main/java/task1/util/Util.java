package task1.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import task1.entity.Document;
import task1.entity.Person;
import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "com.postgresql.cj.jdbc.driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/users");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "12345");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL81Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Document.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}