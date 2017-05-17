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
	name="animaniaces" requestURI="${requestURI}" id="row" excludedParams="*">
	
		
	<display:column>
		<a href="animaniac/view.do?animaniacId=${row.id}">
			<spring:message code="animaniac.view"/>
		</a>
	</display:column>
	
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<jstl:choose>
		    <jstl:when test="${row.banned == true}">
		        <display:column>
					<a href="animaniac/administrator/unban.do?animaniacId=${row.id}">
						<spring:message code="animaniac.unban"/>
					</a>
				</display:column>
		    </jstl:when>    
		    <jstl:otherwise>
		        <display:column>
					<a href="animaniac/administrator/ban.do?animaniacId=${row.id}">
						<spring:message code="animaniac.ban"/>
					</a>
				</display:column>
		    </jstl:otherwise>
		</jstl:choose>
	</security:authorize>
	
	<jstl:if test="${row.banned}">
		<jstl:set var="style" value="color: red;text-decoration:line-through"/>
	</jstl:if>
	
	<jstl:if test="${!row.banned}">
		<jstl:set var="style" value=""/>
	</jstl:if>
	

	<acme:maskedColumn sorteable="true" code="actor.name" text="${row.name}" highlight="${style}" />
	
	<acme:maskedColumn sorteable="true" code="actor.surname" text="${row.surname}" highlight="${style}" />

	<acme:maskedColumn sorteable="true" code="animaniac.genre" text="
	<jstl:if test="${animaniac.genre eq 'male'}">
		<spring:message code="animaniac.male" />
	</jstl:if>
	<jstl:if test="${animaniac.genre eq 'female'}">
		<spring:message code="animaniac.female" />
	</jstl:if><br/>
	" highlight="${style}" />
	
</display:table>

<p><spring:message  code="animaniac.bantip" /></p>

