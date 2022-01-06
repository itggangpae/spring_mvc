package kr.co.adamsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.adamsoft.domain.SpringUser;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (request.getMethod().equals("POST")) {
			HttpSession session = request.getSession();
			session.removeAttribute("LOGIN");
			session.removeAttribute("msg");
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getMethod().equals("POST")) {
			HttpSession session = request.getSession();

			ModelMap modelMap = modelAndView.getModelMap();
			SpringUser SpringUser = (SpringUser) modelMap.get("vo");

			if (SpringUser != null) {
				System.out.println("유저 로그인 성공");
				session.setAttribute("LOGIN", SpringUser);
				Object dest = session.getAttribute("dest");
				response.sendRedirect(dest != null ? (String) dest : "./");
			} else {
				System.out.println("유저 로그인 실패");
				session.setAttribute("msg", "email 이나 비밀번호가 잘못되었습니다.");
			}
		}
	}
}
