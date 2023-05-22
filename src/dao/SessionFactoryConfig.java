package dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionFactoryConfig {
    public static SessionFactory createSessionFactory(Configuration configuration) {
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/microlearn?allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");// if exists
        ServiceRegistryBuilder builder = new ServiceRegistryBuilder(); // creation of new service
        builder.applySettings(configuration.getProperties());// add configuration with properties
        ServiceRegistry serviceRegistry = builder.buildServiceRegistry(); // register service
        return configuration.buildSessionFactory(serviceRegistry); // return SessionFactory
    }
}
