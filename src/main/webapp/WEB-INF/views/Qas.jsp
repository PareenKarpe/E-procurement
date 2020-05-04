<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<title>QAS</title>
<script>
	
</script>

</head>
<body>
 <form:form commandName="quality" method="qas.htm">
			<form:errors path="Fuel_Eff" />
 Fuel_Eff <form:input path="Fuel_Eff" />
	
	
	
		Power_Eff <form:input path="Power_Eff" />
		Engine_Eff <form:input path="Engine_Eff"  />
	Main_Eff <form:input path="Main_Eff"  />
			<input type="Submit"/>




		</form:form> 

</body>
</html>
