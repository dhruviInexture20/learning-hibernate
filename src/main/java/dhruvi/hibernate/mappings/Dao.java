package dhruvi.hibernate.mappings;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class Dao {
	
	private static final Logger logger = Logger.getLogger(Dao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tnx;
	
	// one to one
	void addEmployee(Employee emp) {
		session = sessionFactory.openSession();
		tnx = session.beginTransaction();
		
		session.save(emp);
		
		tnx.commit();
		logger.info(emp.getName() +" added successfully" );
		session.close();
	}
	
	void addObject(Object obj ) {
		session = sessionFactory.openSession();
		tnx = session.beginTransaction();
		
		session.save(obj);
		
		tnx.commit();
		logger.info("object added successfully" );
		session.close();
		
	}
	
	void addQuestion(Question q) {
		session = sessionFactory.openSession();
		tnx = session.beginTransaction();
		
		session.save(q);
		
		tnx.commit();
		logger.info("Question added successfully" );
		session.close();
	}
	
	void addAnswer(Answer ans) {
		session = sessionFactory.openSession();
		tnx = session.beginTransaction();
		
		session.save(ans);
		
		tnx.commit();
		logger.info("Answer added successfully" );
		session.close();
	}

	Question getQuestion(int id) {
		session = sessionFactory.openSession();
		
		Question que = session.get(Question.class, id);
		
		session.close();
		
		return que;
	}
	

	public Employee getEmployee(int id) {
		
		session = sessionFactory.openSession();
		
		Employee emp = session.get(Employee.class, id);
		
		return emp;
	}

	UserProfile getUserById(int id) {
		
		session = sessionFactory.openSession();
		
		UserProfile user = session.get(UserProfile.class, id);
//		session.close();
		return user;
	}

	public Role getRoleById(int id) {
		
		session = sessionFactory.openSession();
		
		Role role = session.get(Role.class, id);
//		session.close();
		
		
		return role;
	}
	
	
	
	
}
