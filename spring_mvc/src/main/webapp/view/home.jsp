<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL Core 기능 사용 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- EL을 사용하기 위한 설정으로 없어도 되는 경우가 많지만 간혹 EL을 가지고 출력이 안되는 경우가 있어서 설정 -->
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시작 페이지</title>

<!-- 웹에서 링크 설정은 주소를 기준으로 합니다. 파일의 위치가 아닙니다. -->
<link rel="stylesheet" type="text/css" href="./css/style.css">

</head>
<body>

	<div align="center" class="body">
		<ul>
			<li><a href="hello" class="menu">인사</a></li>
			<li><a href="message/detail/10" class="menu">디렉토리 패턴의 매개변수</a></li>
			<li><a href="newarticle" class="menu">새글</a></li>
			<li><a href="redirect" class="menu">리다이렉트</a></li>
			<li><a href="header" class="menu">헤더 설정</a></li>
			<c:if test="${userinfo.email == null}">
				<li><a href="user/join" class="menu">회원가입</a></li>
				<li><a href="user/login" class="menu">로그인</a></li>
			</c:if>
			<c:if test="${userinfo.email != null}">
				<li><img
					src="${pageContext.request.contextPath}/profile/${userinfo.image}" />${userinfo.nickname}님
					<a href="user/logout" class="menu">로그아웃</a></li>
				<li><a href="user/update" class="menu">회원 정보 수정</a></li>
			</c:if>
		</ul>
		
		<h3>인터셉터 적용</h3>
		<ul>
			<c:if test="${LOGIN == null }">
				<li><a href="interceptor/login" class="menu">로그인</a></li>
			</c:if>
			<c:if test="${LOGIN != null }">
				<li><a href="interceptor/logout" class="menu">로그아웃</a></li>
			</c:if>
			<li><a href="board/boardlist" class="menu">게시물 보기</a></li>
			<li><a href="board/boardwrite" class="menu">게시물 쓰기</a></li>
			<li><a href="board/noticewrite" class="menu">공지사항 쓰기</a></li>
		</ul>


		<h2>상품 목록 화면</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="80">상품ID</th>
				<th align="center" width="320">상품명</th>
				<th align="center" width="100">가격</th>
			</tr>
			<c:forEach var="item" items="${list}">
				<tr class="record">
					<td align="center">${item.itemid}</td>
					<td align="left"><a href="detail.html?itemid=${item.itemid}">${item.itemname}</a></td>
					<td align="right">${item.price}원</td>
				</tr>
			</c:forEach>
		</table>
		<ul>
			<li><a href="fileview" class="menu">파일 목록보기</a></li>
			<li><a href="item.xls" class="menu">엑셀 다운로드</a></li>
			<li><a href="excelread" class="menu">엑셀 파일읽기</a></li>
			<li><a href="pdf" class="menu">PDF 다운로드</a></li>
			<li><a href="itemlist.json" class="menu">아이템 목록</a></li>

			<li><a href="rest/text" class="menu">텍스트 출력</a></li>
			<li><a href="rest/json" class="menu">JSON 출력</a></li>
			<li><a href="#" class="menu" onclick="ajax();">ajax 출력</a></li>
			<li><a href="item.xml" class="menu">XML 출력</a></li>
			<li><a href="exception" class="menu">예외처리</a></li>
			<li><a href="message" class="menu">스프링 메시지 출력</a></li>
			<li><a href="fileupload" class="menu">파일 업로드 처리</a></li>

		</ul>
		<div id="disp"></div>

	</div>



</body>

<script>
	function ajax() {
		var request = new XMLHttpRequest();
		request.open('get', 'rest/ajax');
		request.send('');
		request.addEventListener('load', function(data) {

			var json = data.target.responseText;
			var list = JSON.parse(json);

			var output = '';
			for (i in list) {
				var item = list[i];
				output += '<span>';
				output += '<h3>' + item.title + '</h3>';
				output += '<p>' + item.content + '</p>';
				output += '</span>';
			}
			document.getElementById('disp').innerHTML = output;
		});
	};
</script>

</html>