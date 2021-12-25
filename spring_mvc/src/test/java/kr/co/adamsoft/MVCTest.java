package kr.co.adamsoft;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import kr.co.adamsoft.domain.SpringUser;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MVCTest {
	@Autowired
	private SqlSession sqlSession;

//	@Test
//	public void mybatistest() throws Exception{
//		//존재하므로 email이 리턴
//		System.out.println(sqlSession.selectOne("springuser.emailcheck", "ggangpae1@gmail.com") +"");
//		//존재하지 않으므로 null 리턴
//		System.out.println(sqlSession.selectOne("springuser.emailcheck", "ggangpae2@gmail.com") + "");
//	}

//	@Test
//	public void mybatistest() throws Exception{
//		//존재하므로 nickname이 리턴
//		System.out.println(sqlSession.selectOne("springuser.nicknamecheck", "군계") +"");
//		//존재하지 않으므로 null 리턴
//		System.out.println(sqlSession.selectOne("springuser.nicknamecheck", "싸움닭") + "");
//	}
	
	@Test
	public void mybatistest() throws Exception{
	    	//성공하면 1리턴
	    	SpringUser springUser = new SpringUser();
	    	springUser.setEmail("ggangpae2@gmail.com");
	    	springUser.setPw("1234");
	    	springUser.setNickname("싸움닭");
	    	springUser.setImage("default.jpg");
	    	System.out.println(sqlSession.insert("springuser.join", springUser) + "");
	}


}