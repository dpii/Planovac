<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>
<c:choose>
	<c:when test="${skupina['new']}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2>
	<c:choose>
	<c:when test="${skupina['new']}">Nová skupina</c:when>
	<c:otherwise>Úprava skupiny</c:otherwise>
	</c:choose>
</h2>
<br/>
<form:form modelAttribute="skupina" method="${method}">
  <table>
    <tr>
      <th>
        Název: <form:errors path="nazev" cssClass="errors"/>
        <br/>
        <form:input path="nazev" size="15" maxlength="20"/>
      </th>
    </tr>
    <tr>
      <th>
        Popis: <form:errors path="popis" cssClass="errors"/>
        <br/>
        <form:input type="textarea" path="popis" size="30" maxlength="200"/>
      </th>
    </tr>
    <tr>
      <th>
        Veřejně přístupná: <form:errors path="verejna" cssClass="errors"/>
        <br/>
        <form:checkbox path="verejna"/>
      </th>
    </tr>
     <tr>
      <td>
        <c:choose>
          <c:when test="${skupina['new']}">
            <p class="submit"><input type="submit" value="Přidej skupinu"/></p>
          </c:when>
          <c:otherwise>
            <p class="submit"><input type="submit" value="Aktualizuj skupinu"/></p>
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    </table>
</form:form>

<c:if test="${!skupina['new']}">
  <form:form method="delete">
    <p class="submit"><input type="submit" value="Smazat skupinu"/></p>
  </form:form>
</c:if>    

<%@ include file="/WEB-INF/views/footer.jsp" %>
