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

<fieldset>

<b><spring:message code="actor.username"/>:</b>
<acme:mask text="${partner.userAccount.username}"/><br/>

<b><spring:message code="actor.name"/>:</b>
<acme:mask text="${partner.name}"/><br/>

<b><spring:message code="actor.surname"/>:</b>
<acme:mask text="${partner.surname}"/><br/>

<b><spring:message code="actor.email"/>:</b>
<jstl:out value="${partner.email}"/><br/>

<b><spring:message code="actor.phone"/>:</b>
<jstl:out value="${partner.phone}"/><br/>

<b><spring:message code="partner.link"/>:</b>
<a href="<acme:mask text="${partner.link}"/>"><acme:mask text="${partner.link}"/></a><br/>

<b><spring:message code="partner.address"/>:</b>
<acme:mask text="${partner.address}"/><br/>

<b><spring:message code="partner.description"/>:</b><br/>
<acme:mask text="${partner.description}"/><br/>

</fieldset>
	<a href="message/write.do?actorId=${partner.id}">
    	<spring:message  code="actor.sendMessage" />
	</a>
<jstl:if test="${owner}">
	<a href="partner/partner/edit.do?partnerId=${partner.id}">
    	<spring:message  code="actor.edit" />
	</a>
</jstl:if>
