
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

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
	
		


<!-- vendor new product-->
		
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
		<form action="${contextPath}/logout" method="GET">
		<input type="submit" value="logout"/>
		</form>
		<form action = "${contextPath}/dashNewProduct" method = "GET" value="">
		
		<input  value="Add New Product"  type="${Vis_Vendor}"/>
		</form>
			<form action = "${contextPath}/Listnewprd" method = "GET" value="">
		<input value="List of products raised" type="${Vis_Vendor}"/>
		</form>
		
		
		
		<!-- Employee and Manager list of prd view -->
		
		<form action ="${contextPath}/dashboard" method="GET">
		
		<input value="List of Products" type="${Vis_Employee}"/>
			<input value="List of products" type="${Vis_Manager}"/>
		</form>
			
			
			
	
			<!-- Manager/employee/qas request list(new + history) -->
			
	
		<form action ="${contextPath}/RequestList" method="GET">
		<input  value="Order History and Status" type="${Vis_Employee}"/>
			<input  value="Order status" type="${Vis_Manager}"/>
			<input  type="${Vis_Vendor}" value="View orders"/>
			<input  value="Handle quality" type="${Vis_Qas}"/>
			</form>
	
			
				<!-- QAS -->
		
			
				
			
			
		
	
	
		 <input  name = "hidden1" value="${value1}"/>
		
		 <div>${message}</div> 
		  <div>${message1}</div> 
	 
</body>
</html>
