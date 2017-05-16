
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="attributes" requestURI="${requestURI}" id="row">
	
	
	<!-- Attributes -->
	
	<spring:message code="attribute.name" var="name" />
	<display:column property="name" title="${name}" sortable="true"/>
	
	<spring:message code="attribute.type" var="type" />
	<display:column property="type.typeName" title="${type}" sortable="true"/>
	
	<!-- Action links -->

	<display:column>
		<a href="attribute/administrator/edit.do?attributeId=${row.id}">
			<spring:message	code="attribute.edit" />
		</a>
	</display:column>
	

</display:table>

<a href="attribute/administrator/edit.do">
	<spring:message	code="attribute.create" />
</a>
