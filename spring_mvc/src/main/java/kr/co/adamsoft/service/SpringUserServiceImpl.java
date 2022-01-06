package kr.co.adamsoft.service;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;
import kr.co.adamsoft.util.CryptoUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Service
public class SpringUserServiceImpl implements SpringUserService {
	@Autowired
	private SpringUserMapper springUserDao;

	@Override
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 결과 초기화
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);
		map.put("emailcheck", false);
		map.put("nicknamecheck", false);

		// 파라미터 읽기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		String key = "itggangpae";
		
		/*
		String emailResult = springUserDao.emailCheck(email);
		if (emailResult == null) {
			map.put("emailcheck", true);
		} else {
			map.put("emailcheck", false);
		}
		 */


		/*
		String emailResult = springUserDao.emailCheck(email);

		if (emailResult == null) {
			map.put("emailcheck", true);
		} else {
			map.put("emailcheck", false);
		}
		*/
		
		List<String> emailList = springUserDao.emailCheck();
		boolean flag = false;
		
		try {
			for(String encryptEmail : emailList) {
				if(email.equals(CryptoUtil.decryptAES256(encryptEmail, "itggangpae"))) {
					flag = true;
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}


		String nicknameResult = springUserDao.nicknameCheck(nickname);
		if (nicknameResult == null) {
			map.put("nicknamecheck", true);
		} else {
			map.put("nicknamecheck", false);
		}
		

		String image = "default.jpg";
		if (flag == false && nicknameResult == null) {
			MultipartFile imageFile = request.getFile("image");
			System.out.println(imageFile.isEmpty());
			if (imageFile.isEmpty() == false) {
				String filePath = request.getServletContext().getRealPath("/profile");
				image = UUID.randomUUID() + imageFile.getOriginalFilename();
				filePath = filePath + "/" + image;
				File file = new File(filePath);
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(file);
				} catch (FileNotFoundException e) {
					System.out.println("파일 생성 실패");
					System.out.println(e.getLocalizedMessage());
				}
				try {
					fos.write(imageFile.getBytes());
				} catch (Exception e) {
					System.out.println("전송 실패");
					System.out.println(e.getLocalizedMessage());
				}
			}

			SpringUser user = new SpringUser();
			try {

				user.setEmail(CryptoUtil.encryptAES256(email, key));
				user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
				user.setNickname(nickname);
				user.setImage(image);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			int r = springUserDao.join(user);
			if (r == 1) {
				map.put("result", true);
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> emailCheck(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();

		String email = request.getParameter("email");
		List<String> emailList = springUserDao.emailCheck();

		boolean flag = false;
		try {
			for(String encryptEmail : emailList) {
				if(email.equals(CryptoUtil.decryptAES256(encryptEmail, "itggangpae"))) {
					flag = true;
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		if(flag == false) {
			map.put("emailcheck", true);
		} else {
			map.put("emailcheck", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> nicknameCheck(HttpServletRequest request, HttpServletResponse response) {
		String nickname = request.getParameter("nickname");
		Map<String, Object> map = new HashMap<>();

		String nicknameResult = springUserDao.nicknameCheck(nickname);
		if (nicknameResult == null) {
			map.put("nicknamecheck", true);
		} else {
			map.put("nicknamecheck", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", false);
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");

		List<SpringUser> list = springUserDao.login();
		String key = "itggangpae";

		try {
			for(SpringUser user : list) {
				if(CryptoUtil.decryptAES256(user.getEmail(), key).equals(email)  && BCrypt.checkpw(pw, user.getPw())) {
					result.put("result", true);
					result.put("email", email);
					result.put("nickname", user.getNickname());
					result.put("image", user.getImage());
					break;
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		request.getSession().setAttribute("userinfo", result);				
		return result;
	}

	@Override
	public Map<String, Object> update(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 결과 초기화
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", false);

		// 파라미터 읽기
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		String image = (String)((HashMap) request.getSession().getAttribute("userinfo")).get("image");

		MultipartFile imageFile = request.getFile("image");
		if (imageFile.isEmpty() == false) {
			String filePath = request.getServletContext().getRealPath("/profile");
			image = UUID.randomUUID() + imageFile.getOriginalFilename();
			filePath = filePath + "/" + image;
			File file = new File(filePath);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e1) {
			}
			try {
				fos.write(imageFile.getBytes());
			} catch (Exception e) {
				System.out.println("전송 실패");
			}
		}

		SpringUser user = new SpringUser();
		try {
			user.setEmail(email);
			user.setPw(BCrypt.hashpw(pw, BCrypt.gensalt()));
			user.setNickname(nickname);
			user.setImage(image);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		int r = springUserDao.update(user);
		if (r == 1) {
			map.put("result", true);
		}
		return map;
	}

}
