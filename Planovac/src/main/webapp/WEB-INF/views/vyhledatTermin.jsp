<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<h2>Vyhledávač vhodných termínů</h2>

<form:form commandName="vyhledavani" method="post">
  <table>
    <tr>
      <th>
        Od data: <form:errors path="odData" cssClass="errors"/>
        <br/>
        <form:input path="odData" size="20" maxlength="19"/>(yyyy-mm-dd)
      </th>
    </tr>
    <tr>
      <th>
        Do data: <form:errors path="doData" cssClass="errors"/>
        <br/>
        <form:input path="doData" size="20" maxlength="19"/>(yyyy-mm-dd)
      </th>
    </tr>
    <tr>
      <th>
        Od hodiny: <form:errors path="odHodiny" cssClass="errors"/>
        <br/>
        <form:input path="odHodiny" size="10" maxlength="2"/>(od 0 do 23)
      </th>
    </tr>
    <tr>
      <th>
        Do hodiny: <form:errors path="doHodiny" cssClass="errors"/>
        <br/>
        <form:input path="doHodiny" size="10" maxlength="2"/>(od 1 do 24)
      </th>
    </tr>
    <tr>
      <th>
        Doba trvání: <form:errors path="delka" cssClass="errors"/>
        <br/>
        <form:input path="delka" size="10" maxlength="10"/>(v minutách)
      </th>
    </tr>
    <tr>
      <th>
        Pro skupinu: <form:errors path="skupina" cssClass="errors"/>
        <br/>
        <form:select path="skupina" items="${skupiny}"/>
      </th>
    </tr>
    <tr>
      <th>
        Nebo jen pro osobní plán: <form:errors path="jenVOsobnimPlanu" cssClass="errors"/>
        <br/>
        <form:checkbox path="jenVOsobnimPlanu"/>
      </th>
    </tr>
    <tr>
      <th>
        Vyhledávat po: <form:errors path="vyhledavaniPo" cssClass="errors"/>
        <br/>
        <form:input path="vyhledavaniPo" size="10" maxlength="4"/>(v minutách)
      </th>
    </tr>
    <tr>
      <td>
            <p class="submit"><input type="submit" value="Vyhledat"/></p>
      </td>
    </tr>
  </table>

</form:form>

<%@ include file="/WEB-INF/views/footer.jsp" %>
