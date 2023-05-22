package dao;

import dataSets.LikedFact;
import dataSets.Fact;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class LikedFactDAO {
    private SessionFactory sessionFactory;

    public LikedFactDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(LikedFact.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(LikedFact dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        try {
            session.save(dataSet);
            trx.commit();
        } catch (ConstraintViolationException c){
            System.out.println("User " + dataSet.getUserId() + " have already liked fact " + dataSet.getFactId());
        }
        session.close();
    }

    List<Fact> getFactOfUser(long user_id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from LikedFact where id_u =:user_id");
        query.setParameter("user_id", user_id);
        List<LikedFact> list = query.list();
        List<Fact> plist = new ArrayList<>();
        if (list.size() != 0) {
            for (LikedFact lp : list) {
                plist.add(new FactDAO().read(lp.getFactId()));
            }
            if (plist.size() != 0) {
                return plist;
            }
        }
        session.close();
        return null;
    }
}
