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
  <li><a href="<spring:url value="/uzivatele" htmlEscape="true" />">Uživatelé</a></li>
</ul>

<%@ include file="/WEB-INF/views/footer.jsp" %>
