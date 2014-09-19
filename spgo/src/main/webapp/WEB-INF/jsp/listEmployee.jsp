<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
	<h2>After Authentication</h2>
	<sec:authentication var="principal" property="principal" />
	<sec:authorize access="isAuthenticated()" >
		<c:set var="webAuth" value="true"/>
	</sec:authorize>

	<c:if test="${webAuth}">
  	 	LoginId:&nbsp;${principal.username}&nbsp;&nbsp;&nbsp;<a href='<c:url value="logout"/>'>Log out</a>
  	</c:if>
  
 	<form action="employee/save" method="post">
 		<input type="hidden" name="id">
 		<table width="500" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px; margin-bottom:30px;">
			<tr>
				<td><label for="loginId">LoginId</label></td>
				<td><input type="text" id="loginId"  name="loginId"/></td>
			</tr>
			<tr>
				<td><label for="password">Password</label></td>
				<td><input type="text" id="password" name="password"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit"/></td>
			</tr>
		</table>
	</form>

	<table width="500" align="center" border="1" style="margin-top:20px; margin-bottom:30px;">
		<tr>
			<th>LoginId</th>
			<th>Password MD5</th>
			<th>Birthday</th>
		</tr>
		<c:forEach var="employee" items="${employeeList}">
			<tr>
				<td>${employee.loginId}</td>
				<td>${employee.password}</td>
				<td>${employee.birthDay}</td>
				<td><input type="button" value="delete" onclick="window.location='employee/delete?id=${employee.id}'"/></td>
			</tr>
		</c:forEach>
	</table>	
</body>
</html>