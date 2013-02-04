<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<h2>Detail události:</h2>
<h3>${udalost.nazev}</h3>
<table>
	<thead>
		<th>Název</th>
		<th>Popis</th>
		<th>Začátek</th>
		<th>Konec</th>
		<th>Skupina</th>
	</thead>
	<td><spring:url value="/udalost/${udalost.idUdalosti}" context="planovac" var="udalostUrl" htmlEscape="true"></spring:url>${udalost.nazev}</a></td>
	<td>${udalost.popis}</td>
	<td>${udalost.zacatek}</td>
	<td>${udalost.konec}</td>

	</tr>
</table>

<%@ include file="/WEB-INF/views/footer.jsp"%>