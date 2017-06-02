
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


<form:form action="curriculum/animaniac/edit.do" modelAttribute="curriculum">
	
	<acme:textarea code="curriculum.educationSection" path="educationSection"/>
	
	<acme:textarea code="curriculum.experienceSection" path="experienceSection"/>
	
	<acme:textarea code="curriculum.hobbiesSection" path="hobbiesSection"/>
	
	<br/>
	
	<acme:submit name="save" code="curriculum.save"/>
	<jstl:if test="${curriculum.id != 0}">
		<acme:submit name="delete" code="curriculum.delete"/>
	</jstl:if>
	<acme:cancel url="actor/myProfile.do" code="curriculum.cancel"/>


</form:form>
