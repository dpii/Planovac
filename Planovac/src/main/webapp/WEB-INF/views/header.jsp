<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<?xml version="1.0" encoding="UTF-8"?>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cz">


<head>

<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<link rel="stylesheet"
	href="<spring:url value="/resources/default.css" context="planovac" htmlEscape="true" />"
	type="text/css" />
<title>Plánovač : skupinový i osobní rozvrh</title>

</head>

<body>
	<div id="header">

		<div id="logo">
			<a href="<spring:url value="/" htmlEscape="true" />"> <img
				src="<spring:url value="/resources/logo.png" htmlEscape="true" />"></a>
		</div>
		<div id="name">

			<a href="<spring:url value="/" htmlEscape="true" />"> <img
				src="<spring:url value="/resources/name.png" htmlEscape="true" />"></a>
		</div>


		<div id="login">
			login: <input type="text" size="10" /> heslo: <input type="text"
				size="10" /> <input type="button" size="10" onclick="whatever"
				value="Přihlásit"></input>
		</div>
	</div>
	</div>
	<div id="main">
		<div id="content">