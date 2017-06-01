
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
	
	
	<spring:message code="dashboard.averageOfMessagesReceivedPerActor"/>: <jstl:out value="${averageOfMessagesReceivedPerActor}"/><br/>
	<spring:message code="dashboard.minOfMessagesReceivedPerActor"/>: <jstl:out value="${minOfMessagesReceivedPerActor}"/><br/>
	<spring:message code="dashboard.maxOfMessagesReceivedPerActor"/>: <jstl:out value="${maxOfMessagesReceivedPerActor}"/><br/>
	<spring:message code="dashboard.averageOfMessagesSentPerActor"/>: <jstl:out value="${averageOfMessagesSentPerActor}"/><br/>
	<spring:message code="dashboard.minOfMessagesSentPerActor"/>: <jstl:out value="${minOfMessagesSentPerActor}"/><br/>
	<spring:message code="dashboard.maxOfMessagesSentPerActor"/>: <jstl:out value="${maxOfMessagesSentPerActor}"/><br/>
	<spring:message code="dashboard.averageOfCommentsWrittenPerActor"/>: <jstl:out value="${averageOfCommentsWrittenPerActor}"/><br/>
	<spring:message code="dashboard.minOfCommentsWrittenPerActor"/>: <jstl:out value="${minOfCommentsWrittenPerActor}"/><br/>
	<spring:message code="dashboard.maxOfCommentsWrittenPerActor"/>: <jstl:out value="${maxOfCommentsWrittenPerActor}"/><br/>
	<spring:message code="dashboard.averageOfCommentsWrittenInCommentable"/>: <jstl:out value="${averageOfCommentsWrittenInCommentable}"/><br/>
	<spring:message code="dashboard.reportedAnimaniacsRatio"/>: <jstl:out value="${reportedAnimaniacsRatio}"/><br/>
	<spring:message code="dashboard.partnerWithMoreBanners"/>: <jstl:out value="${partnerWithMoreBanners.name} ${partnerWithMoreBanners.surname}"/><br/>
	<spring:message code="dashboard.partnerWithHighestFee"/>: <jstl:out value="${partnerWithHighestFee.name} ${partnerWithHighestFee.surname}"/><br/>
	<br>
	<spring:message code="dashboard.certifiedPetRatio"/>: <jstl:out value="${certifiedPetRatio}"/><br/>

	<spring:message code="dashboard.animaniacsByReports" />:<br>
	<display:table pagesize="5" class="displaytag1" name="animaniacsByReports"
		requestURI="${requestURI}" id="row" uid="animaniacsByReports" excludedParams="*">
		
	   	<display:column title="${dashboard.animaniac.name}">
	   	  <jstl:out value="${animaniacsByReports.name}"/>
	   	  <jstl:out value="${animaniacsByReports.surname}"/>
	    </display:column>
	    <acme:column sorteable="false" code="dashboard.animaniac.email" path="email"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.gender" path="genre"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.address" path="address"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.rate" path="rate"/>
	</display:table>
	<br>
		
	<spring:message code="dashboard.animaniacsWith10PercentMoreAcceptedApplicationsThanAvg" />:<br>
	<display:table pagesize="5" class="displaytag1" name="animaniacsWith10PercentMoreAcceptedApplicationsThanAvg"
		requestURI="${requestURI}" id="row" uid="animaniacsWith10PercentMoreAcceptedApplicationsThanAvg" excludedParams="*">
		
	   	<display:column title="${dashboard.animaniac.name}">
	   	  <jstl:out value="${animaniacsWith10PercentMoreAcceptedApplicationsThanAvg.name}"/>
	   	  <jstl:out value="${animaniacsWith10PercentMoreAcceptedApplicationsThanAvg.surname}"/>
	    </display:column>
	    <acme:column sorteable="false" code="dashboard.animaniac.email" path="email"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.gender" path="genre"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.address" path="address"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.rate" path="rate"/>
	</display:table>
	<br>
	
	<spring:message code="dashboard.top3PopularAnimaniacs" />:<br>
	<display:table pagesize="5" class="displaytag1" name="top3PopularAnimaniacs"
		requestURI="${requestURI}" id="row" uid="top3PopularAnimaniacs" excludedParams="*">
		
	   	<display:column title="${dashboard.animaniac.name}">
	   	  <jstl:out value="${top3PopularAnimaniacs.name}"/>
	   	  <jstl:out value="${top3PopularAnimaniacs.surname}"/>
	    </display:column>
	    <acme:column sorteable="false" code="dashboard.animaniac.email" path="email"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.gender" path="genre"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.address" path="address"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.rate" path="rate"/>
	</display:table>
	<br>
	
	<spring:message code="dashboard.top3AnimaniacsByPetNumber" />:<br>
	<display:table pagesize="5" class="displaytag1" name="top3AnimaniacsByPetNumber"
		requestURI="${requestURI}" id="row" uid="top3AnimaniacsByPetNumber" excludedParams="*">
		
	   	<display:column title="${dashboard.animaniac.name}">
	   	  <jstl:out value="${top3AnimaniacsByPetNumber.name}"/>
	   	  <jstl:out value="${top3AnimaniacsByPetNumber.surname}"/>
	    </display:column>
	    <acme:column sorteable="false" code="dashboard.animaniac.email" path="email"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.gender" path="genre"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.address" path="address"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.rate" path="rate"/>
	</display:table>
	<br>
	
	<spring:message code="dashboard.animaniacsWithMorePets" />:<br>
	<display:table pagesize="5" class="displaytag1" name="animaniacsWithMorePets"
		requestURI="${requestURI}" id="row" uid="animaniacsWithMorePets" excludedParams="*">
		
	   	<display:column title="${dashboard.animaniac.name}">
	   	  <jstl:out value="${animaniacsWithMorePets.name}"/>
	   	  <jstl:out value="${animaniacsWithMorePets.surname}"/>
	    </display:column>
	    <acme:column sorteable="false" code="dashboard.animaniac.email" path="email"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.gender" path="genre"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.address" path="address"/>
	    <acme:column sorteable="false" code="dashboard.animaniac.rate" path="rate"/>
	</display:table>
	<br>
</fieldset>
