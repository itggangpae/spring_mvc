
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/user/join.css">
<script src="${pageContext.request.contextPath}/js/user/update.js"></script>
<title>회원가입</title>
</head>
<body>
	<form id="updateform" enctype="multipart/form-data">
		<h2>정보 수정</h2>
		<div align="center" id="msg"></div>
		<ul>
			<li><label for="email">이메일</label> <input type="text" id="email"
				name="email" class="textinput" value="${userinfo.email}"
				readonly="readonly" /></li>

			<li><label for="pw">비밀번호</label> <input type="password" id="pw"
				name="pw" class="textinput" placeholder="비밀번호는 8-15자로 만들어야 합니다." />
			</li>
			<div id="pwmsg"></div>
			<li><label for="pw1">비밀번호확인</label> <input type="password"
				id="pw1" class="textinput"
				placeholder="영문 대소문자와 특수문자와 숫자 1개 이상으로 만들어야 합니다." /></li>

			<li><label for="nickname">별명</label> <input type="text"
				id="nickname" name="nickname" class="textinput" readonly="readonly"
				value="${userinfo.nickname}" /></li>
			<div id="nicknamemsg"></div>
			<li><label for="image">이미지</label> <input type="file" id="image"
				class="fileinput" accept="image/*" name="image" /> <img id="img"
				src="${pageContext.request.contextPath}/profile/${userinfo.image}" />
			</li>
			<li class="buttons"><input type="button" value="정보수정"
				id="updatebtn" /> <input type="button" value="메인" id="mainbtn" /> <input
				type="button" value="로그인" id="loginbtn" /></li>
		</ul>
	</form>
</body>
</html>

