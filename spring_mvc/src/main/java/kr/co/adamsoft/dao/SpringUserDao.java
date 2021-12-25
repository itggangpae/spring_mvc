package kr.co.adamsoft.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.adamsoft.domain.SpringUser;

@Repository
public class SpringUserDao {
	@Autowired
	private SqlSession sqlSession;
	
	/*
	public String emailCheck(String email) {
		return sqlSession.selectOne("springuser.emailcheck", email) ;
	}
	*/
	
	public List<String> emailCheck() {
		return sqlSession.selectList("springuser.emailcheck") ;
	}

	public String nicknameCheck(String nickname) {
		return sqlSession.selectOne("springuser.nicknamecheck", nickname) ;
	}
	
	public int join(SpringUser springUser) {
		return sqlSession.insert("springuser.join", springUser) ;
	}
	
	public List<SpringUser> login() {
		return sqlSession.selectList("springuser.login") ;
	}

	public int update(SpringUser springUser) {
		return sqlSession.update("springuser.update", springUser) ;
	}
}
