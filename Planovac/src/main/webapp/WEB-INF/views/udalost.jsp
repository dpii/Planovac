<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<h2>Detail události:</h2>
<h3>${udalost.nazev} (${udalost.vlastnikUz.login})</h3>

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
	<c:if test="${!(udalost.vlastnikSk==null)}">
      	<td>	
      		<spring:url value="/skupiny/${udalost.vlastnikSk.idSkupiny}" context="planovac" var="skupinaUrl" htmlEscape="true">
			</spring:url>
			<a href="${skupinaUrl}">${udalost.vlastnikSk.nazev}</a>
		  </td>
		</c:if>
	</tr>
</table>

<h3>Seznam účastníků</h3>

<table>
	<thead>
		<th>Login</th>
		<th>Jméno</th>
		<th>Email</th>
	</thead>
	<c:forEach var="uzivatel" items="${udalost.ucastnici}">
		<tr>
			<td><spring:url value="/uzivatele/{idUzivatele}"
					context="planovac" var="uzivatelUrl">
					<spring:param name="idUzivatele" value="${uzivatel.idUzivatele}" />
				</spring:url> <a href="${uzivatelUrl}">${uzivatel.login}</a></td>
			<td>${uzivatel.jmeno} ${uzivatel.prijmeni}</td>
			<td>${uzivatel.email}</td>
		</tr>
	</c:forEach>
</table>

<div>
	<c:if test="${opravneni}">
		<a href="<spring:url value="/udalost/${udalost.idUdalosti}/upravit" htmlEscape="true" />">Upravit událost</a>
		<br/>
	</c:if>
	<c:if test="${ucastniSeUdalosti==1}">
		<a href="<spring:url value="/udalost/${udalost.idUdalosti}/pridatSe" htmlEscape="true" />">Přidat se k události</a>
		<br/>
	</c:if>
	<c:if test="${ucastniSeUdalosti==2}">
		<a href="<spring:url value="/udalost/${udalost.idUdalosti}/odebratSe" htmlEscape="true" />">Odebrat se od události</a>
		<br/>
	</c:if>
	<br/>
<a href="<spring:url value="/uzivatel" htmlEscape="true" />">Zpět</a>

<%@ include file="/WEB-INF/views/footer.jsp"%>