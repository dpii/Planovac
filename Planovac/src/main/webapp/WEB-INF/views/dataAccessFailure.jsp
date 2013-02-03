<%@ include file="/WEB-INF/views/header.jsp" %>

<%
Exception ex = (Exception) request.getAttribute("exception");
%>

<h2>Data access failure: <%= ex.getMessage() %></h2>
<p/>

<%
ex.printStackTrace(new java.io.PrintWriter(out));
%>

<p/>
<br/>

<%@ include file="/WEB-INF/views/footer.jsp" %>
