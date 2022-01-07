<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<h1>개인정보</h1>
<p style="color: blue;">로그인을 했습니다.</p>
<p>회원번호 : ${user.id }</p>
<p>이메일 : ${user.email }</p>
<p>이름 : ${user.name }</p>
<p><a href="./app/user/userList">회원목록</a>
<a href="./app/user/logout">로그아웃</a></p>
</body>
</html>