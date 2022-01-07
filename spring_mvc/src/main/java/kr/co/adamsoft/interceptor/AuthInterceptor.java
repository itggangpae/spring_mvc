package kr.co.adamsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("msg");
		if (session.getAttribute("LOGIN") == null) {
			session.setAttribute("msg", "로그인 해야 이용 가능한 서비스입니다.");
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			if (query == null || query.equals("null")) {
				query = "";
			} else {
				query = "?" + query;
			}
			if (request.getMethod().equals("GET")) {
				request.getSession().setAttribute("dest", uri + query);
			}
			response.sendRedirect("/interceptor/login");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}

