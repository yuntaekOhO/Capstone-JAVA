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
	<%@ include file="/WEB-INF/jsp/Common/top.jsp" %>
	<h2>게시글 목록</h2>
	<p>
		<a href="./app/board/boardForm">글쓰기</a>
	</p>

	<form name="pageForm" action="./app/board/boardList">
		행수: <input type="number" name="count" value="${param.count }" min="1" style="width:50px;"/>
		페이지: <input type="number" id="page" name="page" value="${param.page }" min="1" style="width:50px;" />
		<button type="submit">조회</button>
	</form>
	<p></p>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일시</th>
				<th>글쓴이</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList }">
				<tr>
					<td>${board.seq }</td>
					<td><a href="./app/board/boardInfo?seq=${board.seq }">${board.title }</a></td>
					<td>${board.regdate }</td>
					<td><a href="./app/board/boardInfo?writer=${board.writer }">${board.writer }</a></td>
					<td>${board.cnt }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>