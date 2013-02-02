<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>


	<h3>Přihlášení</h3>
 
	<c:if test="${not empty error}">
		<div class="errorblock">
			Pokus o přihlášení nebyl úspěšný.<br /> Důvod :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
 
	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
 
		<table>
			<tr>
				<td>Login:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Heslo:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Přihlásit" />
				</td>
			</tr>
		</table>
 
	</form>
	
	
<%@ include file="/WEB-INF/views/footer.jsp" %>