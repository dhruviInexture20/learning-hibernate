package dhruvi.hibernate.jpa;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);  
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-jpa.xml");
		
		StudentDao dao = context.getBean("studentDao", StudentDao.class);
		StudentEntity s1 = (StudentEntity) context.getBean("student");
		s1.setS_name("Dhruvi");
		s1.setS_age(20);
		dao.addStudent(s1);
		logger.info("student added..");
		
		
		StudentEntity s2 = context.getBean("student", StudentEntity.class);
		s2.setS_name("Brijesh");
		s2.setS_age(23);
		dao.addStudent(s2);
		logger.info("student added...");
		
		
		// get student details by id
		StudentEntity s3 = context.getBean("student", StudentEntity.class);
		
		s3 = dao.getStudent(2);
		if(s3.getS_id() == 0) {
			logger.info("Student data unavailable");
		}
		else {
			logger.info(s3.getS_id());
			logger.info(s3.getS_name());
			logger.info(s3.getS_age());
		}
		
		
		
		
		// delete s1
		
		dao.deleteStudent(s3);
		logger.info(s3.getS_name() + " is deleted");
		
	}

}




//mvn exec:java -Dexec.mainClass="dhruvi.hibernate.jpa.Main" -Dexec.cleanupDaemonThreads=false
