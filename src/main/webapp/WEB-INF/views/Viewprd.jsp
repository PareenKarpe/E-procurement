<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Edit</title>
	<script>
	div {
		color: red;
	}
	</script>
	</head>
	<body>
	Edit details or delete product
	<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<div>${message}</div>
	<form action="${contextPath}/logout" method="GET">
		<input type="submit" value="logout"/>
		</form>
	<form action = "${contextPath}/UpdatePrd" method = "GET">  
	Name<input name="name" value="${name}"/>
	Description<input name="description" value="${desc}"/>
	Image<img alt="img" src="data:image/jpeg;base64,${image}"/>
	Price<input name="price" value="${price}"/>
	
	Id<input name="id" value="${pid}" type="hidden"/>
	
	*****To change image upload new image here:*********
	
	Photo Upload: <input type="file" name="pic" accept="image/*">
	
	<input type="submit" value="Changesvalues"/>
	
	
	</form>
	To delete
	<form action = "${contextPath}/DelPrd" method = "GET">  
	<input type="submit" value="Delete"/>
	</form>
	
	</body>