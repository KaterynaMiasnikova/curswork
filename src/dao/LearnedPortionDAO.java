package dao;

import dataSets.LearnedPortion;
import dataSets.Portion;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class LearnedPortionDAO {
    private SessionFactory sessionFactory;

    public LearnedPortionDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(LearnedPortion.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(LearnedPortion dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        try {
            session.save(dataSet);
            trx.commit();
        } catch (ConstraintViolationException c){
            System.out.println("User " + dataSet.getUserId() + " have already learned portion " + dataSet.getPortionId());
        }
        session.close();
    }

    List<Portion> getPortionsOfUser(long user_id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from LearnedPortion where id_u =:user_id");//lp inner join lp.portion_id p
        query.setParameter("user_id", user_id);
        List<LearnedPortion> list = query.list();
        List<Portion> plist = new ArrayList<>();
        if (list.size() != 0) {
            for(LearnedPortion lp : list) {
                plist.add(new PortionDAO().read(lp.getPortionId()));
            }
            if (plist.size() != 0) {
                return plist;
            }
        }
        session.close();
        return null;
    }

    List<Portion> getPortionsOfUserOfTheme(long user_id, long theme_id) {
        List<Portion> portions = this.getPortionsOfUser(user_id);
        if (portions != null) {
            portions.removeIf(p -> p.getThemeId() != theme_id);
            if (portions.size() != 0) {
                return portions;
            }
        }
        return null;
    }
}