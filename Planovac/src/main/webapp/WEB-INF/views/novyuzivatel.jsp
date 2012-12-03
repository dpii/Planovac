<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>
<c:choose>
	<c:when test="${uzivatel} = null"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${uzivatel} == 'null'">Novy </c:if>Uzivatel</h2>
<p> test </p>
<br/>
<form:form modelAttribute="uzivatel" method="${method}">
  <table>
    <tr>
      <th>
        Login: <form:errors path="jmeno" cssClass="errors"/>
        <br/>
        <form:input path="jmeno" size="30" maxlength="30"/>
      </th>
    </tr>
    <tr>
      <th>
        Datum narozeni: <form:errors path="jmeno" cssClass="errors"/>
        <br/>
        <form:input path="jmeno" size="10" maxlength="10"/> (yyyy-mm-dd)
      </th>
    </tr>
    <tr>
      <th>
        Type: <form:errors path="mesto" cssClass="errors"/>
        <br/>
        <form:select path="mesto" items="${mesta}"/>
      </th>
    </tr>
    <tr>
      <td>
        <c:choose>
          <c:when test="${uzivatel} == 'null'">
            <p class="submit"><input type="submit" value="Přidej uživatele"/></p>
          </c:when>
          <c:otherwise>
            <p class="submit"><input type="submit" value="Aktualizuj uživatele"/></p>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
  </table>
</form:form>

<%@ include file="/WEB-INF/views/footer.jsp" %>
