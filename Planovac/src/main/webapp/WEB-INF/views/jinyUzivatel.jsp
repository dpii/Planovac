<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<h2>Uživatel ${uzivatel.login} (${uzivatel.jmeno} ${uzivatel.prijmeni}):</h2>

<table>
  <thead>
  	<th>Login</th>
    <th>Jméno</th>
    <th>Email</th>
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
    </tr>
</table>
	
<%@ include file="/WEB-INF/views/footer.jsp" %>