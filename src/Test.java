import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.zd.dao.IUserDao;
import edu.zd.entity.User;

public class Test {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserDao userDAO = (IUserDao)context.getBean("userDAO");
		User user = new User();
		user.setUsername("123");
		user.setPassword("123");
		userDAO.save(user);
	}
}
