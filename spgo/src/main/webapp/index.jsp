<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<table width="500" align="center" cellpadding="0" cellspacing="0" style="margin-top:20px; margin-bottom:30px;">
			<tr>
				<td><a href='<c:url value="login"/>'>Login</a></td>
				<td><a href='<c:url value="guest"/>'>Guest: create new account</a></td>
			</tr>
	</table>
	
	
	
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
