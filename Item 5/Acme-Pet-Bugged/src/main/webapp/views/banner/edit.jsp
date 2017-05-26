<%--
 *
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${requestURI}" modelAttribute="banner">

	<form:hidden path="id" />

	<acme:textbox code="banner.link" path="link"/>
	<acme:textbox code="banner.picture" path="picture"/>
	<br>

	<acme:submit code="banner.save" name="save" />
	<jstl:if test="${banner.id != 0}">
		<acme:submit name="delete" code="banner.delete"/>
	</jstl:if>
	<acme:cancel code="banner.cancel" url="/banner/partner/list.do" />
	
</form:form>