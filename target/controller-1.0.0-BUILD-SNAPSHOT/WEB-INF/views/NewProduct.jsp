<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>New Product</title>
</head>
<body>
<h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<form action = "${contextPath}/createProduct" method = "GET">
Product Name: <input type="text" name="Product_Name"/>



Max Quantity  <input type="number" name="Max_Quantity" step="1"/>

Price   <input type="number" name="Price" />

Vendor Name<input type="text" name="VendorName"/>


 Photo Upload: <input type="file" name="pic" accept="image/*">
 <input type="submit"/>
 
  
 
</form>


</body>
</html>
