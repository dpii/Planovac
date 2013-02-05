<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/includes.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<?xml version="1.0" encoding="UTF-8"?>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cz">


<head>
<script type="text/javascript"
	src="<spring:url value="/resources/scripts/jquery-1.4.1.min.js" htmlEscape="true" />"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/scripts/jquery-ui-1.7.2.custom.min.js" htmlEscape="true" />"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/scripts/jquery.tabs.setup.js" htmlEscape="true" />"></script>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
<link rel="stylesheet"
	href="<spring:url value="/resources/planovac.css" context="planovac" htmlEscape="true" />"
	type="text/css" />

<link rel="stylesheet"
	href="<spring:url value="http://fonts.googleapis.com/css?family=Cantarell:regular,italic,bold,bolditalic" context="planovac" htmlEscape="true" />"
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

		<div id="user_login">


			<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
				<form name='f' action="<c:url value='j_spring_security_check' />"
					method="post">

					<table>
					
						<tr>
							<td>Login:</td>
							<td><input type='text' name='j_username' value=''></td>
							<td>Heslo:</td>
							<td><input type='password' name='j_password' /></td>
							<td colspan='2'><input name="submit" type="submit"
								value="Přihlásit" /></td>
						</tr>
					</table>
					<c:if test="${not empty error}">
								<div id="errorblock">Zadali jste špatně jméno nebo heslo.</div>
							</c:if>
				</form>
				</sec:authorize>
				</div>
		
		

		
		<sec:authorize access="hasRole('ROLE_USER')">
<div id="user_login">
			<a href="<spring:url value="/logout" htmlEscape="true" />">Odhlásit</a> │ <c:out value="${prihlaseny}" />
			</div>
		</sec:authorize>

	</div>

	<div id="main">
		<div id="content">