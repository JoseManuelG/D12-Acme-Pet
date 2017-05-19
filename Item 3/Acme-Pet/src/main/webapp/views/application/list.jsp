
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
	name="applications" requestURI="${requestURI}" id="row">
	
	<!-- Attributes -->
	<jstl:if test="${requestOwner}">
		<spring:message code="application.animaniac" var="codeName" />
		<display:column  title="${codeName}">
				<a href="animaniac/view.do?animaniacId=${row.animaniac.id}">
						<acme:mask text="${row.animaniac.userAccount.username}"/>
				</a>
		</display:column>
	</jstl:if>
	<jstl:if test="${!requestOwner}">
		<spring:message code="application.for" var="codeName" />
		<display:column  title="${codeName}">
				<a href="request/actor/list.do?requestId=${row.request.id}">
					<spring:message code="application.request"/>
				</a>
		</display:column>
	</jstl:if>
	
	<acme:maskedColumn code="application.description" text="${row.description}" sorteable="false"/>
	
	<jstl:choose>
		<jstl:when test="${row.state eq 'Accepted'}">
			<spring:message var="state" code="application.state.accepted" />
		</jstl:when>
		<jstl:when test="${row.state eq 'Pending'}">
			<spring:message var="state" code="application.state.pending" />
		</jstl:when>
		<jstl:when test="${row.state eq 'Denied'}">
			<spring:message var="state" code="application.state.denied" />
		</jstl:when>
	</jstl:choose>
	
	<acme:maskedColumn code="application.state" text="${state}" sorteable="false"/>
	
	
	<!-- Action links -->
	
	<jstl:if test="${requestOwner}">
		<display:column>
			<jstl:if test="${row.state eq 'Pending'}">
				<a href="application/animaniac/accept.do?applicationId=${row.id}">
					<spring:message	code="application.accept" />
				</a> |
				<a href="application/animaniac/deny.do?applicationId=${row.id}">
					<spring:message	code="application.deny" />
				</a>
			</jstl:if>
		</display:column>
	</jstl:if>
</display:table>

