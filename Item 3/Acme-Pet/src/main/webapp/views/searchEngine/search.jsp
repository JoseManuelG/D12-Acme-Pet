
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<fieldset>
	<form:form action="searchEngine/animaniac/search.do" modelAttribute="search">
		<form:hidden path="id" />
		<form:hidden path="animaniac" />
		
		
		
    
		
		<acme:select items="${types}" itemLabel="typeName" code="searchEngine.type" path="type"/>
		<acme:textbox code="searchEngine.address" path="address"/>
		   
		<acme:textbox code="searchEngine.startDate" path="startDate"/>
		<acme:textbox code="searchEngine.endDate" path="endDate"/>
		
		
		
		<acme:submit name="save" code="searchEngine.search"/>
	</form:form>
</fieldset>

<br/>
<p><a href="${banner.link}"><img class="banner" src="${banner.picture}" /></a></p> 
<br/>

<jstl:if test="${!results.isEmpty()}">

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="search.requests" requestURI="${requestURI}" id="row">
	<jstl:set var="owner" value="${row.pets[0].animaniac}"/>
	
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
	
	<spring:message code="request.pets" var="codeName" />
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
	<security:authorize access="hasRole('ANIMANIAC')">
		<display:column>
			
			<jstl:if test="${!(owner eq principal)}">
				<a href="application/animaniac/create.do?requestId=${row.id}">
					<spring:message	code="request.apply" />
				</a>
			</jstl:if>
		</display:column>
	</security:authorize>	
	
</display:table>

</jstl:if>