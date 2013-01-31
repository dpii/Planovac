<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<h2>Uživatelská sekce:</h2>

<table>
  <thead>
  	<th>Login</th>
    <th>Jméno</th>
    <th>Email</th>
    <th>Adresa</th>
    <th>Mesto</th>
    <th>Telefon</th>
  </thead>
    <tr>
      <td>
          <spring:url value="{idUzivatele}" context="planovac" var="uzivatelUrl">
              <spring:param name="idUzivatele" value="${uzivatel.idUzivatele}"/>
          </spring:url>
          <a href="${fn:escapeXml(uzivatelUrl)}">${uzivatel.login}</a>
      </td>
      <td>${uzivatel.jmeno} ${uzivatel.prijmeni}</td>
      <td>${uzivatel.email}</td>
      <td>${uzivatel.adresa}</td>
      <td>${uzivatel.mesto}</td>
      <td>${uzivatel.telefon}</td>
    </tr>
</table>

<a href="<spring:url value="/uzivatel/upravit" htmlEscape="true" />">Upravit údaje</a>
	
<%@ include file="/WEB-INF/views/footer.jsp" %>