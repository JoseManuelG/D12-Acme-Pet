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

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="vets" requestURI="${requestURI}" id="row" excludedParams="*">
	
		
	<display:column>
		<a href="vet/view.do?vetId=${row.id}">
			<spring:message code="vet.view"/>
		</a>
	</display:column>
	
	<acme:maskedColumn sorteable="true" code="actor.name" text="${row.name}" />
	
	<acme:maskedColumn sorteable="true" code="actor.surname" text="${row.surname}" />
	
	<acme:maskedColumn sorteable="true" code="vet.address" text="${row.address}" />
	
	<acme:column sorteable="true" code="actor.phone" path="phone"/>
	
	
</display:table>

