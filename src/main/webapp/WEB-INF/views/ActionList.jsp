<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>List off Products</title>
<script>
	function onClick(clicked_id, val) {

		var divElem = document.getElementById("div1");
		var xmlHttp;

		try // Firefox, Opera 8.0+, Safari
		{
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try // Internet Explorer
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		var textBoxUser = clicked_id;
		alert("kk");
		//CALL THE SERVER-SIDE SCRIPT
		xmlHttp.open("GET", "http://localhost:8080/controller/list", true);
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlHttp.send("user=" + textBoxUser.value);

		//WAIT FOR THE SERVER-SIDE TO RESPOND
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				divElem.innerHTML = xmlHttp.responseText
			}
		}

	}
</script>

</head>
<body>



	<h1>
		<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
		<div class="page-title">Product List</div>
		<form action="${contextPath}/logout" method="GET">
		<input type="submit" value="logout"/>
		</form>
		<form action="${contextPath}/showlistCriteria" method="GET">
		
		Request id<input name="id" type="text"/><input type="submit" value="search"/>
		</form>
		
		<form action="${contextPath}/ActionList1" method="GET">
		<div>${message}</div>
		<li><input type="${typeApp}" name="action" value="Approve"
					visible="false">${Approve}</input></li>
				<li><input type="${typeReject}" name="action" value="Reject">${Reject}</input></li>
				<li><input type="${typeSub}" name="submit" value="submit" /></li>
				<li><input type="${typeDel}" name="action" value="Delivered" >${Delivered}</input></li>
		<!-- vendor to ship -->
			
			
			
		
		<!-- qas response -->
	
		<!-- emp cancels order -->
	<%-- 	<li><input type="hidden" name="action" value="Cancel" >${Cancel}<input></li> --%>
		
			<div class="product-preview-container">
				
				<c:forEach var="movie" items="${requestScope.ActionList}">
					<input type="radio" name="select1" value="${movie.getRequest_ID()}">
					<img alt="img"
						src="data:image/jpeg;base64,${movie.getProduct().getFile1()}" />
					</li>
             Name <li><c:out
							value="${movie.getProduct().getName()}" /></li>  
                Price <li><c:out
							value="${movie.getTotalprice()}" /></li>
								Request Id<li> : <c:out value="${movie.getRequest_ID()}"/></li>
								Status:<li><c:out value="${movie.getStatus()}"/></li>
								
				</c:forEach>

			</div>
		</form>
		</div>
	</h1>

</body>
</html>
