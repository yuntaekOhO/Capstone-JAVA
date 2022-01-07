<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%= request.getContextPath() %>/" />
</head>
<body>
<%@ include file="/WEB-INF/jsp/Common/top.jsp" %>
<%@ include file="/WEB-INF/jsp/User/user.jsp" %>
<h2>회원 상세 조회</h2>
<form>
<table>
	<tr><td>ID</td> <td>${user.id }</td></tr>
	<tr><td>이메일</td> <td>${user.email }</td></tr>
	<tr><td>이름</td> <td>${user.name }</td></tr>
	<tr><td colspan="2">
	<div id="buttons">
	<c:if test="${sessionScope.user.name eq 'admin' || sessionScope.user.name eq user.name }">
		<button type="button" onclick="location.href='./app/user/deleteUser?id=${user.id}';">삭제</button>
		<button type="button" onclick="location.href='./app/user/userEdit?id=${user.id}';">수정</button>
	</c:if>
		<button type="button" onclick="location.href='./app/user/userList${uri}';">회원목록</button>
	</div>
	</td></tr>
</table>
</form>
</body>
</html>