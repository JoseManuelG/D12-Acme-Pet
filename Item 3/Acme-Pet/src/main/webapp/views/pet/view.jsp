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

<b><spring:message code="pet.name"/>:</b>
<acme:mask text="${pet.name}"/><br/>

<b><spring:message code="pet.weigth"/>:</b>
<acme:mask text="${pet.weigth}"/><br/>

<b><spring:message code="pet.certificatedBy"/>:</b>
<acme:mask text="${pet.certificateBy}"/><br/>

<security:authorize access="hasRole('VET')">
<jstl:if test="${pet.certificateBy eq ''}">
	<a href="pet/vet/certificate.do?petId=${pet.id}">
    	<spring:message  code="pet.certificate" />
	</a>
</jstl:if>
</security:authorize>

<b><spring:message code="pet.genre"/>:</b>
<jstl:if test="${pet.genre eq 'male'}">
	<spring:message code="pet.male" />
</jstl:if>
<jstl:if test="${pet.genre eq 'female'}">
	<spring:message code="pet.female" />
</jstl:if><br/>
</fieldset>

<fieldset>

	<h2><acme:mask text="${type.typeName}"/>:</h2>
	<jstl:forEach items="${attributeValues}" var="attributeValue"
		varStatus="j">
		<b><jstl:out value="${attributeValue.attribute.name}"/>:</b>
		<acme:mask text="${attributeValue.value}"/><br/>
	</jstl:forEach>

</fieldset>

<fieldset>

	<h2><spring:message  code="pet.photos" />:</h2>
	<display:table pagesize="5" class="displaytag1" name="photos"
		requestURI="${requestURI}" id="row">
		
		<!-- Attributes -->
		<display:column >
			<acme:image url="${row.link}"/>
		</display:column>
	</display:table>
</fieldset>
<fieldset>

	<h2><spring:message  code="pet.comments" />:</h2>
	<display:table pagesize="5" class="displaytag1" name="comments"
		requestURI="${requestURI}" id="row">

		<!-- Action links -->
		<spring:message code="actor.username" var="actorName" />
	    <display:column title="${actorName}">
	      <a href="animaniac/view.do?animaniacId=${row.animaniac.id}">
	   	  <acme:mask text="${row.animaniac.userAccount.username}"/>
	   	  </a>
	    </display:column>
		<!-- Attributes -->
		<acme:column sorteable="true" code="comment.postMoment" path="postMoment" />

		<acme:column sorteable="true" code="comment.title" path="title" />

		<acme:column sorteable="true" code="comment.body" path="body" />

	</display:table>
	<a href="comment/animaniac/create.do?commentableId=${pet.id}">
    	<spring:message  code="actor.comment" />
	</a>
</fieldset>

<jstl:if test="${owner}">
	<a href="pet/animaniac/edit.do?petId=${pet.id}">
    	<spring:message  code="pet.edit" />
	</a>
</jstl:if>
