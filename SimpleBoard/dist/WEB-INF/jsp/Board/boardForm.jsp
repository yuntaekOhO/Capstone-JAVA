<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<style type="text/css">
 input {
	width: 50%;
} 
textarea {
	width: 50%;
	height: 200px;
}

</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/Common/top.jsp" %>
<h3>글 쓰기</h3>
<form action="./app/board/addBoard" method="post">
<table>
	<tr>
		<td>제목 </td> <td><input type="text" name="title" required autofocus/></td>
	</tr>
	<tr>
		<td>작성자 </td> <td><input type="text" name="writer" required></td>
	</tr>
	<tr>
		<td>본문 </td><td><textarea name="content" required></textarea></td>
	</tr>
	<tr>
		<td colspan="2"><div id="buttons">
			<button type="submit">저장</button>
			<button type="button" onclick="history.back(1)">취소</button>
		</div></td>
	</tr>
</table>
</form>
</body>
</html>