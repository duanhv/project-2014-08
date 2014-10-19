<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="<c:url value="/resources/js/employeeDetails.js" />"></script>
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

	    $('#fileUploadId').change(function() { 
	        // select the form and submit
	        $('#frmUploadFile').submit(); 
	    });
		
	}); 
 	
</script>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="content">
	<div id="imageframe">
			
				<img width="160px" height="160px" src="${contextPath}/resources/uploadprofile/${employee.profileImage}" /> 

				<form method="POST" action="uploadFile" enctype="multipart/form-data" id="frmUploadFile">
			        File to upload: <input type="file" name="file" id="fileUploadId">
			    </form>
			    <br/>
			    
				<a href='${contextPath}/employee/edit' >Edit Profile</a>		
		
	</div>
	<div id="detailsframe">
		<h2>Employee Detail</h2>
		<table >
		<tr>
			<td> Name : </td>
			<td >${employee.name}</td>
		</tr>
		<tr>
			<td> Email : </td>
			<td id="email">${employee.email}</td>
		</tr>
		<tr>
			<td> Gender : </td>
			<td>${employee.gender}</td>
		</tr>
		<tr>
			<td> Age : </td>
			<td>${employee.age}</td>
		</tr>		
		<tr>
			<td> Birth Day : </td>
			<td><fmt:formatDate type="date" value="${employee.birthday}"/></td>
		</tr>
		<tr>
			<td> Phone : </td>
			<td>${employee.phone}</td>
		</tr>		
		<tr>
			<td> Company Name : </td>
			<td>${employee.companyName}</td>
		</tr>	
		<tr>
			<td> Address : </td>
			<td>${employee.address}</td>
		</tr>					

	</table>	
	</div>

</div>


