<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<h2>Uživatelé:</h2>

<table>
  <thead>
    <th>Jméno</th>
    <th>Email</th>
  </thead>
  <c:forEach var="uzivatel" items="${selections}">
    <tr>
      <td>
          <spring:url value="uzivatele/{uzivateleId}" var="uzivatelUrl">
              <spring:param name="uzivatelId" value="${uzivatel.id}"/>
          </spring:url>
          <a href="${fn:escapeXml(uzivatelUrl)}">${uzivatel.jmeno} ${uzivatel.prijmeni}</a>
      </td>
      <td>${uzivatel.email}</td>
    </tr>
  </c:forEach>
</table>

<%@ include file="/WEB-INF/views/footer.jsp" %>