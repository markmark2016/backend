import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
// 琛ㄧず缁ф壙浜哠pringJUnit4ClassRunner绫�
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestUser {
	private static Logger logger = Logger.getLogger(TestUser.class);
	@Resource
	private IUserService ius;

	@Test
	public void tset1() {
		// ius.getUserById(1L);
		User u = new User();
		u.setCity("鍒樿姵瀹�");
		ius.insertUser(u);
	}

}
