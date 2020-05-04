<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>List of Products</title>
	<script>

	function onClick(clicked_id,val)
	{

        var divElem = document.getElementById("div1");
        var xmlHttp;

        try     // Firefox, Opera 8.0+, Safari
        {
            xmlHttp = new XMLHttpRequest();
        }
        catch (e)
        {
            try  // Internet Explorer
            {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch (e)
            {
                try
                {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch (e)
                {
                    alert("Your browser does not support AJAX!");
                    return false;
                }
            }
        }

        var textBoxUser = clicked_id;
        alert("kk");
        //CALL THE SERVER-SIDE SCRIPT
        xmlHttp.open("GET", "http://localhost:8080/controller/list", true);
        xmlHttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlHttp.send("user=" + textBoxUser.value);
        
        //WAIT FOR THE SERVER-SIDE TO RESPOND
        xmlHttp.onreadystatechange=function()
        {
            if(xmlHttp.readyState == 4) {
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
<!-- various criteria -->
<form action="${contextPath}/logout" method="GET">
		<input type="submit" value="logout"/>
		</form>
<form action="${contextPath}/criteriaList" method="GET">

<input type="submit" value="Sort Date(New to Old)"/>
</form>

<form action="${contextPath}/criteriaPriceList" method="GET">

<input type="submit" value="Price(Low to High)"/>
</form>


<form action="${contextPath}/criteriaSearchList" method="GET">
Name/description of the product<input type ="text" name="name"/>
<input type="submit" value="search"/>
</form>


<div>${mess}</div>
     <form action = "${contextPath}/ListProducts" method = "GET">  
      <li><input type="submit" /></li>
      <table border="1">
      <tr><th>Select</th>
      <th>Product Name</th>
      <th>Description</th>
      <th>Price</th>
      <th>Image</th>
      <th>Request date</th></tr>
     
 
            <c:forEach var="movie" items="${requestScope.ProductList}">
             <tr>
            <td>
            <input type="radio" name="select1" value="${movie.getPost_ID()}"> <br>
            </td>
            <td>
               
               <c:out value="${movie.getName()}" />
                 </td><td>
                 
              <c:out value="${movie.getDescription()}" />
                 </td><td>
                   
               <c:out value="${movie.getPrice()}" />
</td><td>
             <img alt="img" src="data:image/jpeg;base64,${movie.getFile1()}"/>
</td>
<td><c:out value="${movie. getPrd_Date()}"/></td>
</tr>
                
            </c:forEach> 
      


</table>
</form>
</div>
 </h1>
 
</body>
</html>
