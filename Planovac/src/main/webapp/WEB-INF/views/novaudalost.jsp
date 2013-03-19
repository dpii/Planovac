<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>
<c:choose>
	<c:when test="${udalost['new']}"><c:set var="method" value="post"/></c:when>
	<c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

<h2>
	<c:choose>
	<c:when test="${udalost['new']}">Nová událost</c:when>
	<c:otherwise>Úprava události</c:otherwise>
	</c:choose>
</h2>
<br/>
<form:form modelAttribute="udalost" method="${method}">
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
        Začátek: <form:errors path="zacatek" cssClass="errors"/>
        <br/>
        <form:input path="zacatek" size="20" maxlength="20"/>(rrrr-MM-dd hh:mm:ss)
      </th>
    </tr>
    <tr>
      <th>
        Konec: <form:errors path="konec" cssClass="errors"/>
        <br/>
        <form:input path="konec" size="20" maxlength="20"/>(rrrr-MM-dd hh:mm:ss)
      </th>
    </tr>
    <tr>
      <th>
        Pro skupinu: <form:errors path="vlastnikSk" cssClass="errors"/>
        <br/>
        <form:select path="vlastnikSk" items="${skupiny}"/>
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
          <c:when test="${udalost['new']}">
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

<c:if test="${!udalost['new']}">
  <form:form method="delete">
    <p class="submit"><input type="submit" value="Smazat událost"/></p>
  </form:form>
</c:if>    

<%@ include file="/WEB-INF/views/footer.jsp" %>
