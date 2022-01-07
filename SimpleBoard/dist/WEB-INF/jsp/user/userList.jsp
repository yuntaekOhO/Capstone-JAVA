<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<script type="text/javascript">
	window.onload = function() { //브라우저가 페이지 로딩을 끝냈을 때 이벤트
		document.getElementById("page").onchange = function() {
			//페이지를 바꿀때 이벤트
			pageForm.submit();
		}
	}
</script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<%@ include file="/WEB-INF/jsp/user/user.jsp" %>
	<h2>회원 목록</h2>
	<form name="pageForm" action="./app/user/userList">
		행수: <input type="number" name="count" value="${param.count }" min="1" style="width:50px;"/>
		페이지: <input type="number" id="page" name="page" value="${param.page }" min="1" style="width:50px;" />
		<button type="submit">조회</button>
	</form>
	<p></p>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>이메일</th>
				<th>이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList }">
				<tr>
					<td>${user.id }</td>
					<td><a href="./app/user/userInfo?id=${user.id }">${user.email }</a></td>
					<td>${user.name }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>