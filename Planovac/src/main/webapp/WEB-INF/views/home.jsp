<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/header.jsp" %>



<%@ page session="false" %>

<div id="featured_slide" class="clear">
    <!-- ###### -->
    <div id="featured_content">
      <div class="featured_box" id="fc1"><img src="<spring:url value="/resources/slides/1.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Zapište si důležité termíny</h2>
          <p>Plánovač je přehledná webová aplikace k vytvoření časového hamonogramu. Plánovač Vám dá vědet o blížícím se termínu nebo schůzce. </p>
          <p class="readmore"><a href="#">Více &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc2"><img src="<spring:url value="/resources/slides/2.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Začněte ihned</h2>
          <p>Vytvořte si svůj účet, přihlašte se a přidávejte nadcházející události. Nejblizší termíny už nezmeškáte. </p>
          <p class="readmore"><a href="#">Více &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc3"><img src="<spring:url value="/resources/slides/3.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Osobní i pracovní využití</h2>
          <p>Plánovač můžete využít k plánování volného času, aktivit s přáteli tak i pracovních schůzek.</p>
          <p class="readmore"><a href="#">Více &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc4"><img src="<spring:url value="/resources/slides/4.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Můj rozvrh</h2>
          <p>Plánovač dokáze najít vhodny termín pro novou událost v rámci skupiny, kam můžete přídat své kolegy a přátele.</p>
          <p class="readmore"><a href="#">Více &raquo;</a></p>
        </div>
      </div>
      <div class="featured_box" id="fc5"><img src="<spring:url value="/resources/slides/5.gif" htmlEscape="true" />">
        <div class="floater">
          <h2>Kdy mají ostatní čas?</h2>
          <p>Plánovač dokáže nalézt vhodný termín pro Vás i Vaše kolegy.</p>
          <p class="readmore"><a href="#">Více &raquo;</a></p>
        </div>
      </div>
    </div>
    <ul id="featured_tabs">
      <li><a href="#fc1">O plánovači</a></li>
      <li><a href="#fc2">Jak začit</a></li>
      <li><a href="#fc3">Vytvořte si svůj rozvrh</a></li>
      <li><a href="#fc4">Plánujte s ostatními</a></li>
      <li class="last"><a href="#fc5">Najděte společné termíny</a></li>
    </ul>

    <!-- ###### -->
  </div>


<h1>
	PPRO 2013
</h1>

<P>  ${serverTime}. Na projektu stále pracujeme.</P>

<ul>
  
  <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
  <li><a href="<spring:url value="/novyuzivatel" htmlEscape="true" />">Registrace</a></li>
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
