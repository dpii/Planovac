<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<h2>Skupina ${skupina.nazev} (${vedouci.jmeno} ${vedouci.prijmeni}):</h2>
	<h3>Popis</h3>
<div>
${skupina.popis}
</div>
<h3>Seznam členů</h3>
<table>
  <thead>
  	<th>Login</th>
    <th>Jméno</th>
    <th>Email</th>
  </thead>
  <c:forEach var="uzivatel" items="${cleni}">
    <tr>
      <td>
          <spring:url value="/uzivatele/{idUzivatele}" context="planovac" var="uzivatelUrl">
              <spring:param name="idUzivatele" value="${uzivatel.idUzivatele}"/>
          </spring:url>
          <a href="${fn:escapeXml(uzivatelUrl)}">${uzivatel.login}</a>
      </td>
      <td>${uzivatel.jmeno} ${uzivatel.prijmeni}</td>
      <td>${uzivatel.email}</td>
    </tr>
    </c:forEach>
</table>
<h3>Seznam událostí skupiny</h3>
<table>
  <thead>
  	<th>Název</th>
  	<th>Popis</th>
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
      <td>${udalost.popis}</td>
      <td>${udalost.zacatek}</td>
      <td>${udalost.konec}</td>
    </tr>
  </c:forEach>
</table>
<div>
	<c:if test="${opravneni}">
		<a href="<spring:url value="/skupiny/${skupina.idSkupiny}/upravit" htmlEscape="true" />">Upravit skupinu</a>
		<br/>
	</c:if>
	<c:if test="${neniVeSkupine==1}">
		<a href="<spring:url value="/skupiny/${skupina.idSkupiny}/pridatSe" htmlEscape="true" />">Přidat se ke skupině</a>
		<br/>
	</c:if>
	<c:if test="${neniVeSkupine==2}">
		<a href="<spring:url value="/skupiny/${skupina.idSkupiny}/odebratSe" htmlEscape="true" />">Odebrat se od skupiny</a>
		<br/>
	</c:if>
	<a href="JavaScript:history.back()">Zpět</a>
</div>
	
<%@ include file="/WEB-INF/views/footer.jsp" %>