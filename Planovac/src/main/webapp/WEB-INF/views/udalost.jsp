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

<div>
	<c:if test="${opravneni}">
		<a href="<spring:url value="/udalost/${udalost.idUdalosti}/upravit" htmlEscape="true" />">Upravit událost</a>
		<br/>
	</c:if>
	<!--
	<c:if test="${ucastniSeUdalosti==1}">
		<a href="<spring:url value="/udalosti/${skupina.idSkupiny}/pridatSe" htmlEscape="true" />">Přidat se ke skupině</a>
		<br/>
	</c:if>
	<c:if test="${neniVeSkupine==2}">
		<a href="<spring:url value="/udalosti/${skupina.idSkupiny}/odebratSe" htmlEscape="true" />">Odebrat se od skupiny</a>
		<br/>
	</c:if>
	-->
	<a href="JavaScript:history.back()">Zpět</a>

<%@ include file="/WEB-INF/views/footer.jsp"%>