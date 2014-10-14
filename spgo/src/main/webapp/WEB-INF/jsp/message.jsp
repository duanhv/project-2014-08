<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test = "${not empty errorMessage }">
     <h2 style="color:red">${errorMessage} </h2> 
</c:if>

<c:if test = "${not empty successMessage }">
     <h2 style="color:blue">${successMessage} </h2> 
</c:if>

