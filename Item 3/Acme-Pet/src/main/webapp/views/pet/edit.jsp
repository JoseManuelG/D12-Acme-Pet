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


<form:form action="pet/animaniac/edit.do" modelAttribute="petForm">
	<%--
	<form:hidden path="errorValue"/>
	<form:hidden path="errorPhoto"/>
	--%>
	<form:hidden path="id"/>
	<form:hidden path="type"/>
	<form:hidden path="attributes"/>
	<acme:textbox code="pet.name" path="name" />
	<form:label path="genre">
		<spring:message code="pet.genre" />
	</form:label>
	<form:select id="genre" name="genre" path="genre">
    	<form:option value="male"><spring:message code="pet.male" /></form:option>
    	<form:option value="female"><spring:message code="pet.female" /></form:option>
    </form:select>
	<br/>
	<acme:textbox code="pet.weigth" path="weigth" />

	<br />
	<jstl:forEach items="${petForm.attributeValues}" var="attributeValue"
		varStatus="j">
		<jstl:out value="${petForm.attributes[j.index].name}"/>:
		<acme:textbox code="pet.nada" path="attributeValues[${j.index}].value" />
		<%-- 
		<jstl:if test="${errorValue eq j.index}">
			<form:errors <form:errors cssClass="error" path="errorValue" />/>
		</jstl:if>
		--%>
	</jstl:forEach>

	<br />
	<jstl:forEach items="${petForm.photos}" var="photo"
		varStatus="i">
		<acme:textbox code="pet.photo.link"
			path="photos[${i.index}].link" />
		<%-- 
		<jstl:if test="${errorPhoto eq j.index}">
			<form:errors <form:errors cssClass="error" path="errorPhoto" />/>
		</jstl:if>
		--%>
	</jstl:forEach>

	<acme:submit code="pet.save" name="save" />

	<acme:submit code="pet.delete" name="delete" />

	<acme:submit code="pet.addPhoto" name="addPhoto" />

	<jstl:if test="${petForm.photos.size()>0}">
		<acme:submit code="pet.removePhoto" name="removePhoto" />
	</jstl:if>

	<acme:cancel code="pet.cancel" url="" />
</form:form>