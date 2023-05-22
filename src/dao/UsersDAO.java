package dao;

import dataSets.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class UsersDAO {
	private SessionFactory sessionFactory;

	public UsersDAO() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Users.class);
		sessionFactory = SessionFactoryConfig.createSessionFactory(configuration);
	}

	public void save(Users dataSet) {
		Session session = sessionFactory.openSession();
		Transaction trx = session.beginTransaction();
		try {
			session.save(dataSet);
			trx.commit();
		} catch (ConstraintViolationException c) {
			System.out.println("User with email " + dataSet.getEmail() + " already exists!");
		}
		session.close();
	}

	public Users read(long id) {
		Session session = sessionFactory.openSession();
		Users result = (Users) session.load(Users.class, id);
		try {
			if (result.getId() == id) {
				return result;
			}
		} catch (ObjectNotFoundException o) {
			System.out.println("\nNo user with id " + id);
		}
		return null;
	}

	public Users getLogin(String email, String pass) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Users where email =:email and password_u =:pass");
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Users> list = query.list();
		if (list.size() != 0) {
			return list.get(0);
		}
		session.close();
		return null;
	}

	public List<Portion> getPortionsOfUser(long user_id) {
		return new LearnedPortionDAO().getPortionsOfUser(user_id);
	}

	public List<Portion> getPortionsOfUserOfTheme(long user_id, long theme_id) {
		return new LearnedPortionDAO().getPortionsOfUserOfTheme(user_id, theme_id);

	}

	public List<Theme> getThemeOfUser(long user_id) {
		return new LearnsThemeDAO().getThemeOfUser(user_id);
	}

	public List<Fact> getFactOfUser(long user_id) {
		return new LikedFactDAO().getFactOfUser(user_id);
	}
}