package kr.co.adamsoft.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface SpringUserService {
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response);
	
	public Map<String, Object> emailcheck(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> nicknamecheck(HttpServletRequest request, HttpServletResponse response);
	
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> update(MultipartHttpServletRequest request, HttpServletResponse response);

}
