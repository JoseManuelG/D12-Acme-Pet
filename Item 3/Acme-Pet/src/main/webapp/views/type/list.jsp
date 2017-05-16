
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
	name="types" requestURI="${requestURI}" id="row">
	
	
	<!-- Attributes -->
	
	<spring:message code="type.name" var="name" />
	<display:column property="typeName" title="${name}" sortable="true"/>
	
	<!-- Action links -->

	<display:column>
		<a href="type/administrator/edit.do?typeId=${row.id}">
			<spring:message	code="type.edit" />
		</a>
	</display:column>
	

</display:table>

<a href="type/administrator/edit.do">
	<spring:message	code="type.create" />
</a>
