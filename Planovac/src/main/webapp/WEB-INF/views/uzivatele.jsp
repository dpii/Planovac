<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp"%>

<h2>Uživatelé:</h2>

	<table>
		<thead>
			<th>Login</th>
			<th>Jméno</th>
			<th>Email</th>
		</thead>
		<c:forEach var="uzivatel" items="${uzivatele}">
			<tr>
				<td><spring:url value="uzivatele/{idUzivatele}"
						var="uzivatelUrl">
						<spring:param name="idUzivatele" value="${uzivatel.idUzivatele}" />
					</spring:url> <a href="${uzivatelUrl}">${uzivatel.login}</a></td>
				<td>${uzivatel.jmeno} ${uzivatel.prijmeni}</td>
				<td>${uzivatel.email}</td>
			</tr>
		</c:forEach>
	</table>



<%@ include file="/WEB-INF/views/footer.jsp"%>