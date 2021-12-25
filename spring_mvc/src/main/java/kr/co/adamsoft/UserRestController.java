package kr.co.adamsoft;

import kr.co.adamsoft.service.SpringUserService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public class UserRestController {
	@Autowired
	private SpringUserService springUserService;
	
	@RequestMapping(value="user/join", method=RequestMethod.POST)
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		return springUserService.join(request, response);
	}
	
	@RequestMapping(value="user/emailcheck", method=RequestMethod.GET)
	public Map<String, Object> emailcheck(HttpServletRequest request, HttpServletResponse response) {
		return springUserService.emailcheck(request, response);
	}
	
	@RequestMapping(value="user/nicknamecheck", method=RequestMethod.GET)
	public Map<String, Object> nicknamecheck(HttpServletRequest request, HttpServletResponse response) {
		return springUserService.nicknamecheck(request, response);
	}
	
	@RequestMapping(value="user/login", method=RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result =  springUserService.login(request, response);
		return result;
	}

	@RequestMapping(value="user/update", method=RequestMethod.POST)
	public Map<String, Object> update(MultipartHttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result =  springUserService.update(request, response);
		request.getSession().invalidate();
		return result;
	}

}
