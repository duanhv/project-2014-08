<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	
	$(document).ready(function() {
		
		$("#employeeForm").validate({
	        rules: {
	        	'currentPassword': { required :true},
	        	'password' 	 : { required :true, minlength: 6, maxlength: 20},
	        	'confirmPassword'   :	{ required : true, minlength: 6, maxlength: 20, equalTo: "#password" }
	        },
	        messages: {
	        	'currentPassword'     : { required : "Please enter your current password"},
	        	'password' : { required : "Please enter password", minlength: "Minimun is 6 characters", maxlength: "Maximun is 20 characters"},
	        	'confirmPassword'   :	{required : "Please enter password", minlength: "Minimun is 6 characters", maxlength: "Maximun is 20 characters", equalTo: "The confirm password must same with the user password"}
	        },
	        submitHandler: function(form) {
	     		form.submit();  
	        }
		});
	}); 
 	
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:if test = "${not empty errorMessage }">
     <h1 style="color:red">${errorMessage} </h1> 
</c:if>

<c:if test = "${not empty successMessage }">
     <h1 style="color:blue">${successMessage} </h1> 
</c:if>

<h2>Change Password</h2>

		<springForm:form method="POST" commandName="employeeForm" id="employeeForm" action="changePassword" >
		<table>
			<tr>
				<th width="20%"></th>
				<th width="40%"></th>
				<td width="40%"></td>
			</tr>
			<tr>
				<td>Current Password:<font color="red">*</font></td>
				<td><springForm:password path="currentPassword" tabindex="2" size="38"/></td>
				<td><springForm:errors path="currentPassword" cssClass="error" /></td>				
			</tr>
			<tr>
				<td>New Password:<font color="red">*</font></td>
				<td><springForm:password path="password" id="password" tabindex="3" size="38"/></td>
				<td><springForm:errors path="password" cssClass="error" /></td>
			</tr>			
			<tr>
				<td>Confirm Password:<font color="red">*</font></td>
				<td><springForm:password path="confirmPassword" tabindex="4" size="38"/></td>
				<td><springForm:errors path="confirmPassword" cssClass="error" /></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input type="submit" value="Save" tabindex="9"></td>
			</tr>
		</table>

	</springForm:form>

