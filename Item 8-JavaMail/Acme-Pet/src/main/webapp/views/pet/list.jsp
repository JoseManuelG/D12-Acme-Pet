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

	<display:table pagesize="5" class="displaytag1" name="pets"
		requestURI="${requestURI}" id="row">

		<!-- Action links -->
		<spring:message code="pet.name" var="name" />
	    <display:column title="${name}">
	      <a href="pet/animaniac/view.do?petId=${row.id}">
	   	  <acme:mask text="${row.name}"/>
	   	  </a>
	    </display:column>
		<!-- Attributes -->
		<jstl:if test="${row.genre eq 'male'}">
			<spring:message var="gender" code="pet.male" />
		</jstl:if>
		<jstl:if test="${row.genre eq 'female'}">
			<spring:message var="gender" code="pet.female" />
		</jstl:if><br/>
		
		<acme:maskedColumn sorteable="true" code="animaniac.genre" text="${gender}"/>

		<acme:column sorteable="true" code="pet.weigth" path="weigth" />
		
	</display:table>
	
</fieldset>

<jstl:if test="${owner}">
	<a href="type/animaniac/select.do">
    	<spring:message  code="pet.create" />
	</a>
</jstl:if>
