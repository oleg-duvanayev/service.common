<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><spring:message code="appl.title"/></title>
	
		<spring:url value="/resources/style.css" var="style"></spring:url>
		<link rel="stylesheet" href="${style}" type="text/css" />
	
		<link rel="stylesheet" href="css/main.css" />
	
		<spring:url value="/resources/js/jquery.1.10.2.min.js" var="jquery"></spring:url>
		
		<spring:url value="/resources/jQuery-TE_v.1.4.0/jquery-te-1.4.0.min.js" var="jqueryte"></spring:url>
		<spring:url value="/resources/jQuery-TE_v.1.4.0/jquery-te-1.4.0.css" var="jquerytecss"></spring:url>
		
		<script type="text/javascript" src="${jquery}"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	
		<link rel="stylesheet" href="${jquerytecss}" />
		<script type="text/javascript" src="${jqueryte}"></script>
	</head>
	
	
	
	<body>
		<div id="header">
			<t:insertAttribute name="header" />
		</div>
		<div id="content">
			<t:insertAttribute name="center" />
		</div>
		<div id="footer">
			<t:insertAttribute name="footer" />
		</div>
		
	</body>
</html>