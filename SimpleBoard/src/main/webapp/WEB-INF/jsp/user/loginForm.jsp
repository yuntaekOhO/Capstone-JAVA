<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<style>
#col1 {
	width: 80px;
}
input {
	width: 150px;
	height: 25px;
}
#table {
	width: 400px;
}
#button {
	margin-left: 3px;
	padding: 22px 22px;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<table id="table" style="border-top: solid 1px #cccccc;">
	<tr><td><h2>로그인</h2></td>
</table>
<form action="./app/user/loginAction" method="post">
	<table id="table">
	<tr>
		<td id="col1">아이디</td>
		<td><input type="text" name="email" required autofocus></td>
		<td rowspan="2"><button type="submit" id="button">로그인</button></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="password" required></td>
	</tr>
	<tr><td colspan="3"><c:if test="${param.mode == 'ERROR' }"><p style="color: red;">로그인 정보가 틀렸습니다.</p></c:if></td></tr>
	<tr>
		<td></td>
		<td><a href="./app/user/createAccount">회원가입</a> &nbsp; <a href="./app/user/">비밀번호 찾기</a></td>
		<td></td>
	</tr>
	</table>
</form>
</body>
</html>