<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<script type="text/javascript">
function checkPassword() {
	var p1 = document.form.password.value;
	var p2 = document.form.repeatPassword.value;
 	if(p1.length < 4){
 		window.alert("비밀번호는 4글자 이상이어야 합니다.");
 		document.getElementById("pw").value= document.getElementById("pw2").value= "";
 		document.getElementById("same").innerHTML="";
 	}
 	if(document.getElementById("pw").value!="" && document.getElementById("pw2").value!="") {
 		if(document.getElementById("pw").value == document.getElementById("pw2").value) {
 			document.getElementById("same").innerHTML="비밀번호가 일치합니다.";
 			document.getElementById("same").style.color = "blue";
 		} else {
 			document.getElementById("same").innerHTML="비밀번호가 일치하지 않습니다.";
 			document.getElementById("same").style.color = "red";
 		}
 	}
}
</script>

</head>
<body>
<%@ include file="/WEB-INF/jsp/common/top.jsp" %>
<form name="form" action="./app/user/addUser" method="post">
<table>
	<tr><td>이메일</td> <td><input type="email" id="email" name="email" required autofocus>
	<c:if test="${param.mode == 'ERROR' }">
		<p style="color: red;">이메일 중복입니다.</p>
	</c:if>
	</td></tr>
	<tr><td>비밀번호</td> <td><input type="password" id="pw" name="password" onchange="checkPassword()" required></td>
	<tr><td>비밀번호 확인</td> <td><input type="password" id="pw2" name="repeatPassword" onchange="checkPassword()" required>
		<span id="same"></span></td></tr>
	<tr><td>이름</td> <td><input type="text" name="name" required></td></tr>
	<tr><td><input type="submit" value="회원가입"></td></tr>
</table>

</form>
</body>
</html>