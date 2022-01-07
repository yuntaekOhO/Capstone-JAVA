<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=request.getContextPath() %>/" />
</head>
<body>
<%@ include file="/WEB-INF/jsp/Common/top.jsp" %>
<h2>회원 정보 수정</h2>
<form action="./app/user/updateUser" method="post">
<table>
	<tr><td>ID</td> <td><input type="text" name="id" value="${user.id }" readonly ></td></tr>
	<tr><td>이메일</td> <td><input type="text" name="email" value="${user.email }" ></td></tr>
	<tr><td>이름</td> <td><input type="text" name="name" value="${user.name }" ></td></tr>
	<tr><td colspan="2">
	<div id="buttons">
		<button type="submit">확인</button>
		<button type="button" onclick="location.href='./app/user/userList${uri}';">목록</button>
	</div>
	</td></tr>
</table>
</form>

</body>
</html>