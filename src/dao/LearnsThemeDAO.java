package dao;

import dataSets.Theme;
import dataSets.LearnsTheme;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class LearnsThemeDAO {
    private SessionFactory sessionFactory;

    public LearnsThemeDAO() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(LearnsTheme.class);
        sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
    }

    public void save(LearnsTheme dataSet) {
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        try {
            session.save(dataSet);
            trx.commit();
        } catch (ConstraintViolationException c){
            System.out.println("User " + dataSet.getUserId() + " already learns theme " + dataSet.getThemeId());
        }
        session.close();
    }

    List<Theme> getThemeOfUser(long user_id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from LearnsTheme where id_u =:user_id");
        query.setParameter("user_id", user_id);
        List<LearnsTheme> list = query.list();
        List<Theme> plist = new ArrayList<>();
        if (list.size() != 0) {
            for (LearnsTheme lp : list) {
                plist.add(new ThemeDAO().read(lp.getThemeId()));
            }
            if (plist.size() != 0) {
                return plist;
            }
        }
        session.close();
        return null;
    }
}