package dhruvi.hibernate.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class StudentDao implements ApplicationContextAware {
	
	private static final Logger logger = Logger.getLogger(StudentDao.class);
	ApplicationContext context ;
	
	private EntityManagerFactory emf;
	private EntityManager em;

	void addStudent(StudentEntity s1) {
		emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		logger.info("in add");
		em.persist(em.contains(s1) ? s1 : em.merge(s1));
		
		em.getTransaction().commit();
		
	}
	
	void deleteStudent(StudentEntity s) {
		emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
//		em.remove(s);
		em.remove(em.contains(s) ? s : em.merge(s));
		logger.info("in delete");
		
		
		em.getTransaction().commit();
	}

	public StudentEntity getStudent(int id) {
		emf = (EntityManagerFactory) context.getBean("entityManagerFactory");
		em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		logger.info("in find");
		StudentEntity student = em.find(StudentEntity.class ,id);
		if(student.getS_id() == 0) {
			logger.info("Student data not available");
		}
		em.getTransaction().commit();
		
		return student;
	}
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	
	

}
