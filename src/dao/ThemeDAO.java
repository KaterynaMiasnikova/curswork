package dao;

import dataSets.Theme;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ThemeDAO {
    private SessionFactory sessionFactory;

    public ThemeDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Theme.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(Theme dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public Theme read(long id) {
        Session session = sessionFactory.openSession();
        Theme result = (Theme) session.load(Theme.class, id);
        try {
            if (result.getId() == id) {
                return result;
            }
        } catch (ObjectNotFoundException o) {
            System.out.println("\nNo theme with id " + id);
        }
        return null;
    }
}
