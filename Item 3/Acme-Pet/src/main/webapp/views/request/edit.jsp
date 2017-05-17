
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="${requestURI}" modelAttribute="request">

	<form:hidden path="id" />
	
	<acme:textbox code="request.startDate" path="startDate"/>
	<acme:textbox code="request.endDate" path="endDate"/>
	<acme:textbox code="request.address" path="address"/>
	<acme:textarea code="request.description" path="description"/>
	<fieldset>
	 	<legend><spring:message code="request.select.pet"/></legend>
		<jstl:forEach items="${availablePets}" var="pet" varStatus="status">
			<form:checkbox path="pets" value="${pet}" label="<a href="/pet/view.do?=${pet.id}">${pet.name}</a>"/>
			<jstl:if test="${(status.index+1)%3==0}">
				<br />
			</jstl:if>
		</jstl:forEach>
		<br />
	</fieldset>
	<br/>
	
	<acme:submit name="save" code="request.save"/>
	<jstl:if test="${request.id != 0}">
		<acme:submit name="delete" code="request.delete"/>
	</jstl:if>
	<acme:cancel url="request/animaniac/list.do" code="request.cancel"/>


</form:form>
