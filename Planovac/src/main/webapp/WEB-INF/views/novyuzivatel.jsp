<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>
<c:choose>
	<c:when test="${uzivatel['new']}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2>
	<c:choose>
	<c:when test="${uzivatel['new']}">Nový uživatel</c:when>
	<c:otherwise>Úprava údajů</c:otherwise>
	</c:choose>
</h2>
<br/>
<form:form modelAttribute="uzivatel" method="${method}">
  <table>
     <tr>
      <th>
        Login: <form:errors path="login" cssClass="errors"/>
        <br/>
        <form:input path="login" size="15" maxlength="15"/>
      </th>
    </tr>
    <tr>
      <th>
        Heslo: <form:errors path="heslo_hash" cssClass="errors"/>
        <br/>
        <form:input path="heslo_hash" size="15" maxlength="15"/>
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
        Telefon: <form:errors path="telefon" cssClass="errors"/>
        <br/>
        <form:input path="telefon" size="15" maxlength="12"/>
      </th>
    </tr>
    <tr>
      <td>
        <c:choose>
          <c:when test="${uzivatel['new']}">
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
