
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

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="requests" requestURI="${requestURI}" id="row">
	
	
	<!-- Attributes -->
	<spring:message code="request.owner" var="codeName" />
	<display:column  title="${codeName}">
		<a href="animaniac/view.do?animaniacId=${row.pets[0].animaniac.id}">
				<acme:mask text="${row.pets[0].animaniac.userAccount.username}"/>
		</a>
	</display:column>
	<acme:maskedColumn code="request.startDate" text="${row.startDate}" sorteable="false"/>
	<acme:maskedColumn code="request.endDate" text="${row.endDate}" sorteable="false"/>
	<acme:maskedColumn code="request.address" text="${row.address}" sorteable="false"/>
	<acme:maskedColumn code="request.description" text="${row.description}" sorteable="false"/>
	
	<spring:message code="request.description" var="codeName" />
	<display:column  title="${codeName}">
		<ul>
		<jstl:forEach items="${row.pets}" var="pet">
			<li><a href="pet/animaniac/view.do?petId=${pet.id}">
				<acme:mask text="${pet.name}"/>
			</a></li>
		</jstl:forEach>
		</ul>
	</display:column>
	
	<!-- Action links -->
	
	<jstl:if test="${owner}">
		<display:column>
			<a href="request/animaniac/edit.do?requestId=${row.id}">
				<spring:message	code="request.edit" />
			</a>
		</display:column>
	</jstl:if>
</display:table>

<a href="request/animaniac/edit.do">
	<spring:message	code="request.create" />
</a>
