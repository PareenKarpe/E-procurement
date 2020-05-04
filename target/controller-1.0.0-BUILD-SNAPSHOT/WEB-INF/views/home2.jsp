
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<style>
	div {color: red;}
	</style>
	
</head>
<body>
<h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	Now login to the system:
	<table>
	<tr>
	<td>
	<form action = "${contextPath}/login" method = "GET">
	</td>
	</tr>
	<tr>
	<td>
	User Name <input name="User_Name" type="text"/>
	
	</td>
	<tr>
	<td>
	
		Password <input name="Password" type="text"/>
		</td></tr>
	
	
	<tr>
	<td>
	
		 <input name="Submit" type="Submit"/>
		</td></tr>
	
	
	
	<tr>
	<td>
	</form>
	<div>${message}</div>
	
	
	
	</td>
	</tr>
	
	
	</table>
	

</body>
</html>
