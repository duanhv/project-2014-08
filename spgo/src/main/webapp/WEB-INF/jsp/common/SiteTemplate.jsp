<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link rel="shortcut icon" href="images/hlb_icon.ico"/>
	<title>Employee Management</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<%-- Default CSS --%>
	<tiles:importAttribute name="defaultcss"/>
	<c:forEach var="item" items="${defaultcss}">
		<link rel="stylesheet" href="<c:url value='${item}'/>" type="text/css" media="screen" />
	</c:forEach>

	<%-- Default JS --%>
	<tiles:importAttribute name="defaultjs"/>
	<c:forEach var="item" items="${defaultjs}">
    	<link rel="stylesheet" href="<c:url value='${item}'/>" type="text/css" media="screen" />
	</c:forEach>

</head>
<body>

		<!-- Header -->
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<!-- Menu Page -->
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		<!-- Body Page -->
		<div id="page">
			<tiles:insertAttribute name="body" />
		</div>
		<!-- Footer Page -->
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
</body>
</html>