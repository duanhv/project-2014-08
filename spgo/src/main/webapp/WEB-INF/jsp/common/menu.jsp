<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<sec:authentication var="principal" property="principal" />
	<sec:authorize access="isAuthenticated()" >
		<c:set var="webAuth" value="true"/>
	</sec:authorize>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<ul>
		<li class="current_page_item"><a href="${contextPath}">Home</a></li>
	  	<li>
	  		<c:if test="${not empty webAuth}">
	  			<a href='${contextPath}/employee/details' >Employee Details</a>
	  		</c:if>
	  	
	  	</li>
	  	<li>
	  		<c:if test="${webAuth}">
	  			<a href='${contextPath}/employee/changePassword' >Change Password</a>
	  		</c:if>
	  	</li>
		<li>
		  	<c:choose>
			    <c:when test="${webAuth}">
			       	<a href='${contextPath}/logout'>Log out</a>
			    </c:when>
			    <c:otherwise>
			        <a href='${contextPath}/login'>Login</a>
			    </c:otherwise>
			</c:choose>
	  	</li>
	  	<li>
	  		<c:if test="${empty webAuth}">
	  			<a href='${contextPath}/employee/save' >Create Account</a>
	  		</c:if>
	  	
	  	</li>

	</ul>
