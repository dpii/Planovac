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
        Login: <form:errors path="login" cssClass="errors"/>
        <br/>
        <form:input path="login" size="30" maxlength="30"/>
      </th>
    </tr>
    <tr>
      <th>
        Jméno: <form:errors path="jmeno" cssClass="errors"/>
        <br/>
        <form:input path="jmeno" size="20" maxlength="20"/>
      </th>
    </tr>
    <tr>
      <th>
        Příjmení: <form:errors path="prijmeni" cssClass="errors"/>
        <br/>
        <form:input path="prijmeni" size="20" maxlength="20"/>
      </th>
    </tr>
    <tr>
      <th>
        E-mail: <form:errors path="email" cssClass="errors"/>
        <br/>
        <form:input path="email" size="20" maxlength="30"/>
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
