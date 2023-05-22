package dao;

import dataSets.Fact;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FactDAO {
    private SessionFactory sessionFactory;

    public FactDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Fact.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(Fact dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public Fact read(long id) {
        Session session = sessionFactory.openSession();
        Fact result = (Fact) session.load(Fact.class, id);
        try {
            if (result.getId() == id) {
                return result;
            }
        } catch (ObjectNotFoundException o) {
            System.out.println("\nNo fact with id " + id);
        }
        return null;
    }
}