<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${!empty sessionScope.user }">
	<p>
		<a href="./app/user/userInfo?id=${sessionScope.user.id }">${sessionScope.user.name }</a> |
		 <a href="./app/user/logout">로그아웃</a>
	</p>
</c:if>
<c:if test="${empty sessionScope.user }">
	<p>
		<a href="./app/user/createAccount">회원가입</a> | 
		<a href="./app/user/loginForm">로그인</a>
	</p>
</c:if>