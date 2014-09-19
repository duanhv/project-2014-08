<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	<h2>Guest.....</h2>
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
				<td colspan="2"><input type="submit" value="Create New Employee"/></td>
			</tr>
		</table>
	</form>
</body>
</html>