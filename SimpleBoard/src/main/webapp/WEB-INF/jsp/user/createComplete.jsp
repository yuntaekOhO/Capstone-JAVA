<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<h2>회원가입 완료</h2>
<p>회원가입을 완료했습니다.</p>
<p>이메일 : ${user.email } </p>
<p>이름 : ${user.name }</p>
<p>
<a href="./app/user/loginForm">로그인</a>
</p>
</body>
</html>