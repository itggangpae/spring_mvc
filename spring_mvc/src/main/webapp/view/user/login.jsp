<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/user/login.css">
<script src="${pageContext.request.contextPath}/js/user/login.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<div class="inner_login">
		<div class="login_myweb">
			<div id="msg" class="msg"></div>
			
			<form method="post" id="loginform">
				<fieldset>
					<legend class="screen_out">로그인</legend>
					<div class="box_login">
						<div class="inp_text">
							<label for="email" class="screen_out">이메일</label> <input
								type="email" id="email" name="email" placeholder="이메일을 입력하세요!">
						</div>
						<div class="inp_text">
							<label for="pw" class="screen_out">비밀번호</label> <input
								type="password" id="pw" name="pw" placeholder="비밀번호를 입력하세요!">
						</div>
					</div>
				</fieldset>
			</form>
			
			<button class="btn_login" id="loginbtn">로그인</button>
			
			<div class="login_append">
				<div class="inp_chk">
					<input type="checkbox" id="keepLogin" class="inp_radio"
						name="keepLogin"> <label for="keepLogin" class="lab_g">
						<span class="img_top ico_check"></span> <span class="txt_lab">로그인
							상태 유지</span>
					</label>
				</div>
			</div>

			<div class="login_append" align="center">
				<a href="#" class="link_find">아이디 찾기</a> <a href="#"
					class="link_find">비밀번호 찾기</a> <a href="join" class="link_find">회원가입</a>
				<a href="../index.html" class="link_find">메인으로</a>
			</div>
		</div>
	</div>
</body>
</html>