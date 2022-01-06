package kr.co.adamsoft;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleTesting {
	
	@Autowired
	private SpringUserMapper springUserMapper;

	
	@Test
	public void mybatistest() throws Exception{
	    	//성공하면 1리턴
	    	SpringUser springUser = new SpringUser();
	    	springUser.setEmail("ggangpae2@gmail.com");
	    	springUser.setPw("1234");
	    	springUser.setNickname("싸움닭");
	    	springUser.setImage("default.jpg");
	    	System.out.println(springUserMapper.join(springUser));
	}

}