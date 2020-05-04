<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!--new user  -->
<html>
<head>
	<title>Addition User</title>
	<style>
div {
	color: red;
}
</style>
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
<form action="${contextPath}/logout" method="GET">
		<input type="submit" value="logout"/>
		</form>
<table>
<tr>
<div>${msg}</div>
 <form action = "${contextPath}/create" method = "GET">
 <td>
         First Name: <input type = "text" name = "First_Name">
         <td />
         <td>
         Last Name: <input type = "text" name = "Last_Name" /></td>
         <td>
          Phone Number: <input type = "number" name = "Phone_Number" />
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
		
		Date_Birth: <input type = "Date" name = "Date_Birth" />
		</td>
		<td>
          
    
         
         
         
         
         User Name: <input type = "text" name = "User_Name"  id="userName" />   <div id="div1"></div>
          <div>${userValid}</div>
         </td>
		
		<td>
          
  
         
         
         
        Password:  <input type = "text" name = "Password" />
         </td>
		
		<td>
		Type:<select name="Type">
  <option value="Employee">Employee</option>
  <option value="Manager">Manager</option>
  <option value="QAS">QAS</option>
   <option value="Vendor">Vendor</option>
  
</select>
		
	 <input type = "submit" value = "Submit" />	
		</td>
		
		
		<!-- <td>
          
    
         
         
       <a href="account.htm">Click Here to go to the USER FORM VIEW</a>
         
         <input type = "submit" value = "Submit" />
         </td> -->
      </form>
      </tr>
      </table>
</body>
</html>
