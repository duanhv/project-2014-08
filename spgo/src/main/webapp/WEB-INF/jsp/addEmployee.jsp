<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>home page </title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="<c:url value="/resources/css/default.css"/>" rel="stylesheet"/>
</head>
<body>
<!-- start header -->
<div id="header">
	<h1><a href="#">Portal Page</a></h1>
	<p><a href="http://saigontechnology.vn/">Spring 4 + Mongodb</a></p>
</div>
<!-- end header -->
<!-- star menu -->
<div id="menu">
	<ul>
		<li class="current_page_item"><a href="#">Home</a></li>
		<li><a href="#">Blog</a></li>
		<li><a href="#">Photos</a></li>
		<li><a href="#">About</a></li>
		<li><a href="#">Contact</a></li>
	</ul>
</div>
<!-- end menu -->
<!-- start page -->
<div id="page">
<h2>Guest.....</h2>
		<springForm:form method="POST" commandName="employeeForm" action="employee/save">
		<table>
			<tr>
				<td>Name:</td>
				<td><springForm:input path="name" /></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><springForm:input path="email" /></td>
				<td><springForm:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>PassWord:</td>
				<td><springForm:input path="password" /></td>
				<td><springForm:errors path="password" cssClass="error" /></td>
			</tr>			
			<tr>
				<td>Confirm PassWord:</td>
				<td><springForm:input path="confirmPassword" /></td>
				<td><springForm:errors path="confirmPassword" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><springForm:input path="age" /></td>
				<td><springForm:errors path="age" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><springForm:select path="gender">
						<springForm:option value="" label="Select Gender" />
						<springForm:option value="MALE" label="Male" />
						<springForm:option value="FEMALE" label="Female" />
					</springForm:select></td>
				<td><springForm:errors path="gender" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td><springForm:input path="birthday" placeholder="MM/dd/yyyy"/></td>
				<td><springForm:errors path="birthday" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><springForm:input path="phone" /></td>
				<td><springForm:errors path="phone" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Create new Employee"></td>
			</tr>
		</table>

	</springForm:form>
	
	
</div>
<!-- end page -->
<!-- start footer -->
<div id="footer">
	<p class="legal"> &copy;2014 :  Spring 4 + Mongodb
		&nbsp;&nbsp;&bull;&nbsp;&nbsp;
		Design by <a href="http://saigontechnology.vn/">Saigon technology solutuon.</a> </p>
</div>
<!-- end footer -->

</html>
