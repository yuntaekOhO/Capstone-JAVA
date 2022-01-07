<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%= request.getContextPath() %>/" >

<style>
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<h2>게시글 상세 조회</h2>
<form>
<table>
	<tr>
		<td>제목 </td> <td>${board.title }</td>
	</tr>
	<tr>
		<td>작성자 </td> <td>${board.writer }</td>
	</tr>
	<tr>
		<td>내용 </td> <td>${board.content }</td>
	</tr>
	<tr><td colspan="2">
	<div id="buttons">
		<button type="button" onclick="location.href='./app/board/deleteBoard?seq=${board.seq}';">삭제</button>
		<button type="button" onclick="location.href='./app/board/boardEdit?seq=${board.seq}';">수정</button>
		<button type="button" onclick="location.href='./app/board/boardList${uri}';">목록</button>
	</div>
	</td></tr>
</table>
</form>
</body>
</html>