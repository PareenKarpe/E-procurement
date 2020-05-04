
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!--login  -->
<html>
<head>
<title>Reset password</title>
<style>
div {
	color: red;
}
</style>

</head>
<body>
	<h1>
	
	


	Check your email for token.
	Enter your username and new password
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
		<form action = "${contextPath}/newPassword" method = "GET">
		
		User name<input name="User_Name" type="text"/>
		New Password <input name="Password" type="text"/>
		Token<input name="token"type="text"/>
		<input type="submit"/>
		</form>
		 <div>${message}</div>
	 
	 </form>
</body>
</html>
