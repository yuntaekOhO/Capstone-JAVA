<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<h3>글 수정</h3>
<form action="./app/board/updateBoard" method="post">
<table>
	<tr>
		<td>번호 </td> <td><input type="text" name="seq" value="${board.seq }" required readonly/></td>
	</tr>
	<tr>
		<td>제목 </td> <td><input type="text" name="title" value="${board.title }" required autofocus/></td>
	</tr>
	<tr>
		<td>작성자 </td> <td><input type="text" name="writer" value="${board.writer }" readonly></td>
	</tr>
	<tr>
		<td>본문 </td><td><textarea name="content" required>${board.content }</textarea></td>
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