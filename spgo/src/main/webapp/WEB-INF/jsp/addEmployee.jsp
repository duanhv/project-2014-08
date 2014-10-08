<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
	
	$(document).ready(function() {


		
		$("#employeeForm").validate({
	        rules: {
	        	'name'      : { required : true},
	        	'email'     : { required :true, email : true},
	        	'password' : { required : true, minlength: 6, maxlength: 20},
	        	'confirmPassword'   :	{ required : true, minlength: 6, maxlength: 20, equalTo: "#password" }
	        },
	        messages: {
	        	'name'      : { required : "Please enter your name"},
	        	'email'     : { required : "Please enter email", email : "Email is invalid"},
	        	'password' : { required : "Please enter password", minlength: "Minimun is 6 characters", maxlength: "Maximun is 20 characters"},
	        	'confirmPassword'   :	{required : "Please enter password", minlength: "Minimun is 6 characters", maxlength: "Maximun is 20 characters", equalTo: "The confirm password must same with the user password"}
	        },
	        submitHandler: function(form) {
	     		form.submit();  
	        }
		});
 	
	    $('#picker').dropdate({
	        dateFormat:'mm/dd/yyyy'
	    });
		
	}); 
 	
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<c:if test = "${not empty userExist }">
     <h1 style="color:red">${userExist} </h1> 
</c:if>
<h2>Register Employee</h2>

		<springForm:form method="POST" commandName="employeeForm" id="employeeForm" action="save" >
		<table>
			<tr>
				<th width="20%"></th>
				<th width="40%"></th>
				<td width="40%"></td>
			</tr>
			<tr>
				<td>Name:<font color="red">*</font></td>
				<td><springForm:input path="name" tabindex="1" size="38"/></td>
				<td><springForm:errors path="name" cssClass="error"/></td>
			</tr>
			<tr>
				<td>Email:<font color="red">*</font></td>
				<td><springForm:input path="email" tabindex="2" size="38"/></td>
				<td><springForm:errors path="email" cssClass="error" /></td>				
			</tr>
			<tr>
				<td>Password:<font color="red">*</font></td>
				<td><springForm:password path="password" id="password" tabindex="3" size="38"/></td>
				<td><springForm:errors path="password" cssClass="error" /></td>
			</tr>			
			<tr>
				<td>Confirm Password:<font color="red">*</font></td>
				<td><springForm:password path="confirmPassword" tabindex="4" size="38"/></td>
				<td><springForm:errors path="confirmPassword" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><springForm:input path="age" tabindex="5" size="38"/></td>
				<td><springForm:errors path="age" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><springForm:select path="gender" tabindex="6">
						<springForm:option value="" label="Select Gender" />
						<springForm:option value="MALE" label="Male"/>
						<springForm:option value="FEMALE" label="Female" />
					</springForm:select></td>
				<td><springForm:errors path="gender" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td><springForm:input path="birthday"  id="picker"/></td>
				<td><springForm:errors path="birthday" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><springForm:input path="phone" tabindex="8" size="38"/></td>
				<td><springForm:errors path="phone" cssClass="error" /></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input type="submit" value="Create new Account" tabindex="9"></td>
			</tr>
		</table>

	</springForm:form>

