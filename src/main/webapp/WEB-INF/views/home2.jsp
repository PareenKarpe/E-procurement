
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!--login  -->
<html>
<head>
<title>Home</title>
<style>
div {
	color: red;
}
</style>

</head>
<body>
	<h1>
	
		<%-- <form:form commandName="account" method="account.htm">
			<form:errors path="User_Name" />
	User Name <form:input path="User_Name" />
	
	
	
		Password <form:input path="Password" />

			<input type="Submit"/>




		</form:form> --%>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
		
		<form action = "${contextPath}/login" method = "GET">
		
		User name<input name="User_Name" type="text"/>
		Password <input name="Password" type="password"/>
		<input type="submit"/>
		</form>
		 <div>${message}</div>
	 <input type="hidden" name = "hidden1" value="${value1}"/>
	 ******************************************************************************************************
	 <!-- Reset password -->
	 	<form action = "${contextPath}/resetPassword" method = "GET">
		Enter User name<input name="User_Name1" type="text"/>
	 <input type="submit" value="Forgot password"/>
	 </form>
</body>
</html>
