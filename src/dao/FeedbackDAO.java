package dao;

import dataSets.Feedback;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FeedbackDAO {
    private SessionFactory sessionFactory;

    public FeedbackDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Feedback.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(Feedback dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public Feedback read(long id) {
        Session session = sessionFactory.openSession();
        Feedback result = (Feedback) session.load(Feedback.class, id);
        try {
            if (result.getId() == id) {
                return result;
            }
        } catch (ObjectNotFoundException o) {
            System.out.println("\nNo feedback with id " + id);
        }
        return null;
    }
}