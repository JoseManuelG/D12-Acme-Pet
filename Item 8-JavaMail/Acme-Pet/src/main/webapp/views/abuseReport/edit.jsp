
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


<form:form action="abuseReport/animaniac/edit.do" modelAttribute="abuseReport">

	<form:hidden path="reported"/>

	<acme:textbox code="abuseReport.description" path="description"/>
	
	<br/>
	
	<acme:submit name="save" code="abuseReport.save"/>
	
	<acme:cancel url="animaniac/view.do?animaniacId=${abuseReport.reported.id}" code="abuseReport.cancel"/>


</form:form>
