<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>
<c:choose>
	<c:when test="${udalost['new']}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2><c:if test="${udalost['new']}">Nová </c:if>Událost</h2>
<p> test </p>
<br/>
<form:form modelAttribute="udalost" method="${method}">
  <table>
    <tr>
      <th>
        Nazev: <form:errors path="nazev" cssClass="errors"/>
        <br/>
        <form:input path="nazev" size="15" maxlength="15"/>
      </th>
    </tr>
    <tr>
      <th>
        Začátek: <form:errors path="zacatek" cssClass="errors"/>
        <br/>
        <form:input path="zacatek" size="15" maxlength="15"/>
      </th>
    </tr>
    <tr>
      <th>
        Konec: <form:errors path="konec" cssClass="errors"/>
        <br/>
        <form:input path="konec" size="20" maxlength="30"/>
      </th>
    </tr>
     <tr>
      <td>
        <c:choose>
          <c:when test="${uzivatel['new']}">
            <p class="submit"><input type="submit" value="Přidej událost"/></p>
          </c:when>
          <c:otherwise>
            <p class="submit"><input type="submit" value="Aktualizuj událost"/></p>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    </table>
</form:form>

<%@ include file="/WEB-INF/views/footer.jsp" %>
