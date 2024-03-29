<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<h1><a href="${contextPath}/home">Portal Page</a></h1>
<p><a href="http://saigontechnology.vn/">Spring 4 + Mongodb</a></p>
	<sec:authentication var="principal" property="principal" />
	<sec:authorize access="isAuthenticated()" >
		<c:set var="webAuth" value="true"/>
	</sec:authorize>

	<c:if test="${webAuth}">
  	 	     Welcome :  ${principal}
  	</c:if>
