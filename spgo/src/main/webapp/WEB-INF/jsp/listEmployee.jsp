<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<table width="500" align="center" border="1" style="margin-top:20px; margin-bottom:30px;">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>Active</th>
			<th>Birthday</th>
		</tr>
		<c:forEach var="employee" items="${employeeList}" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${employee.name}</td>
				<td>${employee.email}</td>
				<td>${employee.gender}</td>
				<td>${employee.active}</td>
				<td>
					<fmt:formatDate type="date" value="${employee.birthDay}"/>
				</td>
				<td><input type="button" value="delete" onclick="window.location='employee/delete?id=${employee.id}'"/></td>
			</tr>
		</c:forEach>
	</table>	
