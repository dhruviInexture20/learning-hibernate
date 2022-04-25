package dhruvi.hibernate.mappings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	BasicConfigurator.configure();
        logger.info("Hello");
        
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Dao dao = context.getBean("dao", Dao.class);
        
        Employee emp1 = context.getBean("Dhruvi", Employee.class);
        
        // one to one
        // add employee with contact details
//        dao.addEmployee(emp1);
        dao.addObject(emp1);
        
        
        // many to one
        Question q1 = (Question) context.getBean("question");
        q1.setQuestion("What is Java ?");
        List<Answer> answers = new ArrayList<Answer>();
        
        Answer a1 = (Answer) context.getBean("answer");
        a1.setAnswer("Java is programming Language");
        a1.setQuestion(q1);
        answers.add(a1);
        
        Answer a2 = (Answer) context.getBean("answer");
        a2.setAnswer("Java is OOP language");   
        a2.setQuestion(q1);
        answers.add(a2);
        
        q1.setAnswers(answers);
//        dao.addQuestion(q1);
        dao.addObject(q1);
        
        
        // Many to Many
        
        Role admin = (Role) context.getBean("role");
        admin.setRoleName("admin");
        
        Role projectManager = (Role) context.getBean("role");
        projectManager.setRoleName("Project Manager");
        
        Role srdeveloper = (Role) context.getBean("role");
        srdeveloper.setRoleName("sr. Developer");
        
        Role jrDeveloper = (Role) context.getBean("role");
        jrDeveloper.setRoleName("jr. Developer");
        
        List<Role> roles = new ArrayList<Role>();
        Collections.addAll(roles, admin, projectManager, srdeveloper, jrDeveloper);
        
        
        UserProfile user1 = (UserProfile) context.getBean("user");
        user1.setUsername("dhruvipatel");
        user1.setPassword("Dhruvi@123");
        user1.setRoles(roles);
        
        List<UserProfile> users = new ArrayList<UserProfile>();
        users.add(user1);
       
        admin.setUsers(users);
        projectManager.setUsers(users);
        srdeveloper.setUsers(users);
        jrDeveloper.setUsers(users);
        
        
        dao.addObject(user1);
        
        
       
        // fetching data
        
        UserProfile user =dao.getUserById(6);
        logger.info(user);
        
        
        logger.info(user.getUsername());
        List<Role> userRoles = user.getRoles();
        
        logger.info(userRoles.stream().map(myRole -> myRole.getRoleName()).collect(Collectors.toList()));
        
        
        Role role = dao.getRoleById(10);
        logger.info(" ----- role ----");
        logger.info(role.getRoleName());
        List<UserProfile> userList = role.getUsers();
        logger.info(userList.stream().map(myUser -> myUser.getUsername()).collect(Collectors.toList()) );
        
        // fetching employee 
        Employee emp = dao.getEmployee(1);
        
        logger.info(" -- Employee --");
        logger.info(emp.getName());
        logger.info(emp.getContact().getEmail());
        logger.info(emp.getContact().getSkype());
        
        
        // fetching question and its all answers
        Question que = dao.getQuestion(3);
        
        logger.info(" --- question --- ");
        logger.info(que.getQuestion());
        logger.info(" --- answers ---");
        List<Answer> ansList = que.getAnswers();
        logger.info(ansList.stream().map(ans -> ans.getAnswer()).collect(Collectors.toList()));
        
        context.close();
        
    }
}


//mvn exec:java -Dexec.mainClass="dhruvi.hibernate.mappings.App"
