 <%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
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


    
<jstl:if test="${isNew}">
   	<a target="_blank" href="law/terms-conditions.do">
	<spring:message code="actor.terms" />
	</a>

	<br/>
	
	<acme:checkbox code="actor.AceptTerms" path="acepted"/>

</jstl:if>
   
<acme:submit code="actor.save" name="save"/>

<jstl:if test="${!isNew}">
	<acme:submit code="actor.delete" name="delete"/>
</jstl:if>

<acme:cancel url="" code="actor.cancel"/>
	