<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="pet/edit.do" modelAttribute="petForm">
	<form:hidden path="certificatedBy"/>

	<acme:textbox code="pet.name" path="name" />
	<acme:textbox code="pet.genre" path="genre" />
	<acme:textbox code="pet.weigth" path="weigth" />

	<br />
	<jstl:forEach items="${petForm.attributeValues}" var="attributeValue"
		varStatus="j">
		<acme:textbox code="<jstl:out value="${attributeValue.attribute.name}"/>"
			path="attributeValues[${j.index}].value" />
	</jstl:forEach>

	<br />
	<jstl:forEach items="${petForm.photos}" var="photo"
		varStatus="i">
		<acme:textbox code="pet.photo.link"
			path="photos[${i.index}].link" />
	</jstl:forEach>

	<acme:submit code="pet.save" name="save" />

	<acme:submit code="pet.addPhoto" name="addPhoto" />

	<jstl:if test="${petForm.photos.size()>0}">
		<acme:submit code="pet.removePhoto" name="removePhoto" />
	</jstl:if>

	<acme:cancel code="pet.cancel" url="" />
</form:form>