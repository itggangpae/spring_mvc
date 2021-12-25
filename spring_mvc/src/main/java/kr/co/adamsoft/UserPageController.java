package kr.co.adamsoft;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.adamsoft.service.SpringUserService;

@Controller
public class UserPageController {
	@Autowired
	private SpringUserService springUserService;
	
	@RequestMapping(value="user/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value="user/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="user/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:../";
	}
	
	@RequestMapping(value="user/update", method=RequestMethod.GET)
	public String update() {
		return "user/update";
	}
	



}
