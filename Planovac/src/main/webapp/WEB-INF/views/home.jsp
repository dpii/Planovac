<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>



<%@ page session="false" %>
<br><br><br><br><br><br><br>
<h1>
	Hello world!  test znaků ěščřžýáíéČŘŤ
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul>
  <li><a href="<spring:url value="/novyuzivatel" htmlEscape="true" />">Registrace</a></li>
  <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
	<li><a href="<spring:url value="/login" htmlEscape="true" />">Login</a></li>
  </sec:authorize>
  <li><a href="<spring:url value="/uzivatele" htmlEscape="true" />">Výpis uživatelů</a></li>
  <li><a href="<spring:url value="/udalosti" htmlEscape="true" />">Výpis událostí</a></li>
  <sec:authorize access="hasRole('ROLE_USER')">
	<li><a href="<spring:url value="/uzivatel" htmlEscape="true" />">Uživatelská sekce</a></li>
	<li><a href="<spring:url value="/logout" htmlEscape="true" />">Logout</a></li>
  </sec:authorize>
</ul>

<%@ include file="/WEB-INF/views/footer.jsp" %>
