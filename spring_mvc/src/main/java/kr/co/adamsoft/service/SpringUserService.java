package kr.co.adamsoft.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface SpringUserService {
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response);
	
	//이메일 중복 검사를 위한 메서드
	public Map<String, Object> emailCheck(
			HttpServletRequest request, 
			HttpServletResponse response);
	//닉네임 중복 검사를 위한 메서드
	public Map<String, Object> nicknameCheck(
			HttpServletRequest request, 
			HttpServletResponse response);
	
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response);
	public Map<String, Object> update(MultipartHttpServletRequest request, HttpServletResponse response);

}
