<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
#msg {
	color: red;
}
</style>
</head>
<body>
	<div align="center">
		<h3>로그인</h3>
		<form method="post">
			<span id="msg">${msg}</span>
			<div>
				<input type="email" name="email" placeholder="이메일을 입력하세요"
					required="required" />
			</div>

			<div>
				<input type="password" name="pw" placeholder="비밀번호를 입력하세요"
					required="required" />
			</div>

			<div>
				<input type="submit" value="로그인" />
			</div>
		</form>
	</div>
</body>
</html>