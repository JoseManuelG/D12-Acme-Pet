<%--
 * index.jsp
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
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:image url="${animaniac.picture}"/><br/>

<fieldset>

<b><spring:message code="actor.username"/>:</b>
<acme:mask text="${animaniac.userAccount.username}"/><br/>

<b><spring:message code="animaniac.rate"/>:</b>
<acme:mask text="${animaniac.rate}"/><br/>

<b><spring:message code="actor.name"/>:</b>
<acme:mask text="${animaniac.name}"/><br/>

<b><spring:message code="animaniac.surname"/>:</b>
<acme:mask text="${animaniac.surname}"/><br/>

<b><spring:message code="animaniac.email"/>:</b>
<acme:mask text="${animaniac.email}"/><br/>

<b><spring:message code="animaniac.phone"/>:</b>
<acme:mask text="${animaniac.phone}"/><br/>

<b><spring:message code="animaniac.genre"/>:</b>
<jstl:if test="${animaniac.genre eq 'male'}">
	<acme:mask text="${animaniac.male}"/>
</jstl:if>
<jstl:if test="${animaniac.genre eq 'female'}">
	<acme:mask text="${animaniac.female}"/>
</jstl:if>

<b><spring:message code="animaniac.address"/>:</b>
<acme:mask text="${animaniac.address}"/><br/>

</fieldset>

<!-- TODO comments list-->

<jstl:if test="${owner}">
	<a href="animaniac/edit.do?animaniacId=${animaniac.id}">
    	<spring:message  code="actor.edit" />
	</a>
</jstl:if>
