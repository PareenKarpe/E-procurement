<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        xmlHttp.open("POST", "http://localhost:8080/controller/list", true);
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
     <%-- <form action = "${contextPath}/ListProducts" method = "GET">  --%>  
      <form>
 <div class="product-preview-container">
            <c:forEach var="movie" items="${requestScope.ProductList}">
            <ul>
               <li>
                   <c:out value="${movie.getName()}" /></li>
<li><input type="submit" id="${movie.getPost_ID()}" value="${contextPath}" onClick="onClick(this.id,this.value)"/></li>
          <li>   <img alt="img" src="data:image/jpeg;base64,${movie.getFile1()}"/></li>

                
            </c:forEach> 
      
</div>
</form>
</div>
 </h1>
 
</body>
</html>
