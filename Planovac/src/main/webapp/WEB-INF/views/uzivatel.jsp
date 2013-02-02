<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<h2>Uživatelská sekce:</h2>

<h3>Údaje o uživateli</h3>
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
          <spring:url value="/uzivatel" context="planovac" var="uzivatelUrl" htmlEscape="true">
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
	
	<h3>Nejbližší události</h3>
<table>
  <thead>
  	<th>Název</th>
    <th>Začátek</th>
    <th>Konec</th>
  </thead>
  <c:forEach var="udalost" items="${seznamUdalosti}">
    <tr>
      <td>
          <spring:url value="/udalost/${udalost.idUdalosti}" context="planovac" var="udalostUrl" htmlEscape="true">
			</spring:url>
          <a href="${fn:escapeXml(udalostUrl)}">${udalost.nazev}</a>
      </td>
      <td>${udalost.zacatek}</td>
      <td>${udalost.konec}</td>
    </tr>
  </c:forEach>
</table>
<a href="<spring:url value="/novaudalost" htmlEscape="true" />">Přidat událost</a>
<a href="<spring:url value="/uzivatel/udalosti" htmlEscape="true" />">Všechny události</a>

	<h3>Skupiny</h3>
<table>
  <thead>
  	<th>Název</th>
  </thead>
  <c:forEach var="skupina" items="${seznamSkupin}">
    <tr>
      <td>
          <spring:url value="/skupiny/${skupina.idSkupiny}" context="planovac" var="skupinaUrl" htmlEscape="true">
			</spring:url>
          <a href="${fn:escapeXml(skupinaUrl)}">${skupina.nazev}</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a href="<spring:url value="/novaskupina" htmlEscape="true" />">Vytvořit novou skupinu</a>
<br/>
<a href="<spring:url value="/vyhledatTermin" htmlEscape="true" />">Najít volné termíny</a>

<%@ include file="/WEB-INF/views/footer.jsp" %>