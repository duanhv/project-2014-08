<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()" >
	<c:set  var="webAuth" value="true"/>
</sec:authorize>
 
<script type="text/javascript">

</script>

  <br />

  <form action="j_spring_security_check" method="POST" id="loginForm" name="loginForm">
	  <table width="500" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px; margin-bottom:30px;">
		<tr>
			<td colspan="2">
				Please enter your email and Password
			</td>
		</tr>
	  	<tr>
	  		<td colspan="2">
		  		<c:if test="${not empty param.login_error}">
				    <div class="error">
				        Your login attempt was not successful, try again.<br />
				        Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
				    </div>
				</c:if>
		  	</td>
	  	</tr>
	    <tr>
	      <td>Login ID :</td>
	      <td><input name="j_username" type="text" class="txtfield" id="j_username"  tabindex="1"/> </td>
	    </tr>
	    <tr>
	      <td>Password : </td>
	      <td><input name="j_password" type="password" class="txtfield" id="j_password"  tabindex="2"/></td>
	    </tr>
	    <tr>
	      <td colspan="1"><input type="submit" class="btnlogin"  value="Login"/>
	      </td>
	      <td><a href='<c:url value="employee/save"/>'>Create new account</a></td>
	    </tr>
	  </table>
  </form>
