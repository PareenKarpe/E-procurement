<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>
<html><head><titke>New Product</title>
<style>
div {
	color: red;
}
</style>
</head>
<body>
<h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<form action="${contextPath}/logout" method="GET">
		<input type="submit" value="logout"/>
		</form>
<form action = "${contextPath}/createProduct" method = "GET">
Product Name: <input type="text" name="Product_Name"/>
<br>
Description :<input type="text" name="Description"/>

<!-- Max Quantity  <input type="number" name="Max_Quantity" step="1"/> -->

<br>


<!-- Vendor Name<input type="text" name="VendorName"/> -->
Price<input type="text" name="Price"/>

<br>
 Photo Upload: <input type="file" name="pic" accept="image/*">
 <br>
  <div>${message}</div>
  <br>
 <input type="submit"/>
 
  <br>
 
</form>


</body>
</html>
