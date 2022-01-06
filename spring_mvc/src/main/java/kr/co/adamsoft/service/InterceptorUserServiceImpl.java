package kr.co.adamsoft.service;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;
import kr.co.adamsoft.util.CryptoUtil;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class InterceptorUserServiceImpl implements InterceptorUserService {

	//@Autowired
	private SpringUserMapper springUserDao;
	
	@Override
	public SpringUser login(SpringUser springUser) {
		String email = springUser.getEmail();
		String pw = springUser.getPw();

		List<SpringUser> list = springUserDao.login();
		String key = "itggangpae";

		SpringUser springuser = new SpringUser();
		try {
			for(SpringUser user : list) {
				if(CryptoUtil.decryptAES256(user.getEmail(), key).equals(email)  && BCrypt.checkpw(pw, user.getPw())) {
					springuser.setEmail(email);
					springuser.setImage(user.getImage());
					springuser.setNickname(user.getNickname());
					
					
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return springuser;
	}
}
