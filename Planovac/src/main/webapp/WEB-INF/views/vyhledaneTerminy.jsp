<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<h2>Vyhledané termíny:</h2>

<table>
  <thead>
  	<th>Začátek</th>
    <th>Konec</th>
    <th>Nalezené konflikty</th>
  </thead>
  <c:forEach var="udalost" items="${vysledky}">
    <tr>
      <td>${udalost.zacatek}</td>
      <td>${udalost.konec}</td>
      <td>${udalost.nazev}</td>
    </tr>
  </c:forEach>


<%@ include file="/WEB-INF/views/footer.jsp" %>