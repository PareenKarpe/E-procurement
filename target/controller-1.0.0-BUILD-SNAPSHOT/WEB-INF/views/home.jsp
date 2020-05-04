<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Addition User</title>
	<script>
	 function handleEvent() {
               //var divElem = document.getElementById("div1");

if(document.getElementsByName("Email_Id").length<1)
	{

	//divElem.innerHTML ="cjhsdcjsd";
	alert("em");
		return false;
	}

if(document.getElementsById("userName").length<1)
{
	alert("user");
	return false;
//divElem.innerHTML ="user1";
}

                
                      //  divElem.innerHTML = xmlHttp.responseText
                    


            }
	</script>
</head>
<body>
<h1>
	Addition of User and Account 
</h1>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%-- <a href="${contextPath}/create">Create users and categories</a> --%>
<table>
<tr>
 <form action = "${contextPath}/create" method = "GET" onsubmit="handleEvent()">
 <td>
         First Name: <input type = "text" name = "First_Name">
         <td />
         <td>
         Last Name: <input type = "text" name = "Last_Name" /></td>
         <td>
          Phone Number: <input type = "text" name = "Phone_Number" />
          </td>
           <td>
         Email Id: <input type = "text" name = "Email_Id" />
		</td>
          
          <td>
         Apartment_Name: <input type = "text" name = "Apartment_Name" />
		</td>
		<td>
	 Street_Name : <input type = "text" name = "Street_Name" />
		</td>
		<td>
	 Unit_Number: <input type = "text" name = "Unit_Number" />
		</td>
		<td>
	 City: <input type = "text" name = "City" />
		</td>
		<td>
		 state: <input type = "text" name = "State" />
		 </td>
		 <td>
		
	 postal: <input type = "text" name = "Postal" />
		</td>
		<td>
		 Country: <input type = "text" name = "Country" />
</td>
<td>
		
		Date_Birth: <input type = "text" name = "Date_Birth" />
		</td>
		<td>
          
    
         
         
         
         
         User Name: <input type = "text" name = "User_Name"  id="userName" />   <div id="div1"></div>
         </td>
		
		<td>
          
  
         
         
         
        Password:  <input type = "text" name = "Password" />
         </td>
		
		
		
		
		
		
		
		
		<td>
          
    
         
         
         
         
         <input type = "submit" value = "Submit" />
         </td>
      </form>
      </tr>
      </table>
</body>
</html>
