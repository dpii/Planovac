<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>



<%@ page session="false" %>

<div id="featured_slide" class="clear">
    <!-- ###### -->
    <div id="featured_content">
      <div class="featured_box" id="fc1"><img src="<spring:url value="/resources/slides/1.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Nullamlacus dui ipsum</h2>
          <p>Attincidunt vel nam a maurisus lacinia consectetus magnisl sed ac morbi. Inmaurisus habitur pretium eu et ac vest penatibus id lacus parturpis.</p>
          <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc2"><img src="<spring:url value="/resources/slides/2.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Aliquatjusto quisque nam</h2>
          <p>Attincidunt vel nam a maurisus lacinia consectetus magnisl sed ac morbi. Inmaurisus habitur pretium eu et ac vest penatibus id lacus parturpis.</p>
          <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc3"><img src="<spring:url value="/resources/slides/3.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Aliquatjusto quisque nam</h2>
          <p>Attincidunt vel nam a maurisus lacinia consectetus magnisl sed ac morbi. Inmaurisus habitur pretium eu et ac vest penatibus id lacus parturpis.</p>
          <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc4"><img src="<spring:url value="/resources/slides/4.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Aliquatjusto quisque nam</h2>
          <p>Attincidunt vel nam a maurisus lacinia consectetus magnisl sed ac morbi. Inmaurisus habitur pretium eu et ac vest penatibus id lacus parturpis.</p>
          <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc5"><img src="<spring:url value="/resources/slides/5.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Dapiensociis temper donec</h2>
          <p>Attincidunt vel nam a maurisus lacinia consectetus magnisl sed ac morbi. Inmaurisus habitur pretium eu et ac vest penatibus id lacus parturpis.</p>
          <p class="readmore"><a href="#">Continue Reading &raquo;</a></p>
        </div>
      </div>
    </div>
    <ul id="featured_tabs">
      <li><a href="#fc1">All About The University</a></li>
      <li><a href="#fc2">Why You Should Study With Us</a></li>
      <li><a href="#fc3">Education And Student Experience</a></li>
      <li><a href="#fc4">Alumni And Its Donors</a></li>
      <li class="last"><a href="#fc5">Latest University News &amp; Events</a></li>
    </ul>

    <!-- ###### -->
  </div>


<h1>
	Hello world!  test znaků ěščřžýáíéČŘŤ
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul>
  <li><a href="<spring:url value="/novyuzivatel" htmlEscape="true" />">Registrace</a></li>
  <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
	<li><a href="<spring:url value="/login" htmlEscape="true" />">Login</a></li>
  </sec:authorize>
  <li><a href="<spring:url value="/uzivatele" htmlEscape="true" />">Výpis uživatelů</a></li>
  <li><a href="<spring:url value="/udalosti" htmlEscape="true" />">Výpis událostí</a></li>
  <sec:authorize access="hasRole('ROLE_USER')">
	<li><a href="<spring:url value="/uzivatel" htmlEscape="true" />">Uživatelská sekce</a></li>
	<li><a href="<spring:url value="/logout" htmlEscape="true" />">Logout</a></li>
  </sec:authorize>
</ul>

<%@ include file="/WEB-INF/views/footer.jsp" %>
