<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
	
	<h2>Události:</h2>
	<h3>${nadpis}</h3>

<table>
  <thead>
  	<th>Název</th>
  	<th>Popis</th>
    <th>Začátek</th>
    <th>Konec</th>
    <th>Skupina</th>
  </thead>
  <c:forEach var="udalost" items="${seznamUdalosti}">
    <tr>
      <td>
          <spring:url value="/udalost/${udalost.idUdalosti}" context="planovac" var="udalostUrl" htmlEscape="true">
			</spring:url>
          <a href="${udalostUrl}">${udalost.nazev}</a>
      </td>
      <td>${udalost.popis}</td>
      <td>${udalost.zacatek}</td>
      <td>${udalost.konec}</td>
      <c:if test="${!(udalost.vlastnikSk==null)}">
      	<td>	
      		<spring:url value="/skupiny/${udalost.vlastnikSk.idSkupiny}" context="planovac" var="skupinaUrl" htmlEscape="true">
			</spring:url>
			<a href="${skupinaUrl}">${udalost.vlastnikSk.nazev}</a>
		  </td>
		</c:if>
    </tr>
  </c:forEach>
</table>

<c:if test="${!verejne}">
<a href="<spring:url value="/novaudalost" htmlEscape="true" />">Přidat událost</a>
<br/>
<a href="<spring:url value="/vyhledatTermin" htmlEscape="true" />">Najít volné termíny</a>
<br/>
<a href="<spring:url value="/uzivatel" htmlEscape="true" />">Zpět na detail uživatele</a>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>