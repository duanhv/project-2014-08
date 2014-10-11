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

<div id="content">
	<div id="imageframe">
	</div>
	<div id="detailsframe">
	</div>
	<table>
		<tr>
			<td>${employee.name}</td>
		</tr>
		<tr>
			<td>${employee.email}</td>
		</tr>
		<tr>
			<td>${employee.gender}</td>
		</tr>
		<tr>
			<td><fmt:formatDate type="date" value="${employee.birthday}"/></td>
		</tr>
		<tr>
			<td>
				<form method="POST" action="uploadFile" enctype="multipart/form-data">
			        File to upload: <input type="file" name="file"><br />
			        <input type="submit" value="Upload"> Press here to upload the picture!
			    </form>
			</td>
		</tr>
		<tr>
			<td>
				<img src="${contextPath}/resources/uploadprofile/${employee.profileImage}" /> 
			</td>
		</tr>
	</table>
</div>


