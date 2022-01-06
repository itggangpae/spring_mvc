package kr.co.adamsoft;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.adamsoft.domain.SpringUser;
import kr.co.adamsoft.service.InterceptorUserService;

@Controller
public class InterceptorUserController {
	@RequestMapping(value = "/interceptor/login", method = RequestMethod.GET)
	public String login() {
		return "interceptor/login";
	}
	
	//@Autowired
	private InterceptorUserService interceptorUserService;

	@RequestMapping(value = "interceptor/login", method = RequestMethod.POST)
	public void login(SpringUser SpringUser, Model model) {
		SpringUser vo = interceptorUserService.login(SpringUser);
		model.addAttribute("vo", vo);
	}
	
	@RequestMapping(value = "/interceptor/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
}