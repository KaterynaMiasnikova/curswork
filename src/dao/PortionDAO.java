package dao;

import dataSets.Portion;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PortionDAO {
    private final SessionFactory sessionFactory;

    public PortionDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Portion.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(Portion dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(dataSet);
        trx.commit();
        session.close();
    }

    public Portion read(long id) {
        Session session = sessionFactory.openSession();
        Portion result = (Portion) session.load(Portion.class, id);
        try {
            if (result.getId() == id) {
                return result;
            }
        } catch (ObjectNotFoundException o) {
            System.out.println("\nNo user with id " + id);
        }
        return null;
    }

    public List<Portion> getPortionsOfTheme(long theme_id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Portion where id_t =:theme_id");
        query.setParameter("theme_id", theme_id);
        List<Portion> list = query.list();
        if (list.size() != 0) {
            return list;
        }
        session.close();
        return null;
    }
}
