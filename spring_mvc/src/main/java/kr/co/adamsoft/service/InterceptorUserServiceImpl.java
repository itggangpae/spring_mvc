package kr.co.adamsoft.service;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;
import kr.co.adamsoft.util.CryptoUtil;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterceptorUserServiceImpl implements InterceptorUserService {

	@Autowired
	private SpringUserMapper springUserDao;
	
	@Override
	public SpringUser login(SpringUser springUser) {
		String email = springUser.getEmail();
		String pw = springUser.getPw();

		List<SpringUser> list = springUserDao.login();
		String key = "itggangpae";

		SpringUser loginUser = null;
		try {
			for(SpringUser user : list) {
				if(CryptoUtil.decryptAES256(user.getEmail(), key).equals(email)  && BCrypt.checkpw(pw, user.getPw())) {
					loginUser = new SpringUser();
					loginUser.setEmail(email);
					loginUser.setImage(user.getImage());
					loginUser.setNickname(user.getNickname());
					
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return loginUser;
	}
}
