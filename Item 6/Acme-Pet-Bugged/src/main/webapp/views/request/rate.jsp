
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="request/animaniac/rate.do" modelAttribute="rateForm">

	<form:hidden path="requestId"/>
	<i><spring:message code="request.rate.msg"/> 
		<a href="animaniac/view.do?animaniacId=${animaniac.id}">
				<acme:mask text="${animaniac.userAccount.username}"/>
		</a>
	</i>
	
	<acme:textbox code="request.rate.label" path="rate"/>
	
	<acme:submit name="save" code="request.rate"/>
	<acme:cancel url="request/animaniac/list.do" code="request.cancel"/>


</form:form>
