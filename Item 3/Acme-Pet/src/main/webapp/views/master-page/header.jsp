<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="<spring:url value='/' />">
		<img src="images/logo.png" height="200"  alt="Acme Pet Co., Inc." />
	</a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<li>
			<a class="fNiv"> 
				<spring:message code="master.page.people" /> 
			</a>
			<ul>
				<li class="arrow"></li>
				<li><a href="animaniac/list.do"><spring:message code="master.page.list.animaniac" /></a></li>
				<li><a href="vet/list.do"><spring:message code="master.page.list.vet" /></a></li>
				<security:authorize access="isAuthenticated()">
					<li><a href="partner/list.do"><spring:message code="master.page.list.partner" /></a></li>
				</security:authorize>
			</ul>
		</li>
		
		<li>
			<a class="fNiv"> 
				<spring:message code="master.page.pets" /> 
			</a>
			<ul>
				<li class="arrow"></li>
				<li><a href="pet/listAll.do"><spring:message code="master.page.pet.list" /></a></li>
				<security:authorize access="hasRole('ANIMANIAC')">
					<li><a href="pet/animaniac/myPets.do"><spring:message code="master.page.pet.myPets" /></a></li>
				</security:authorize>
			</ul>
		</li>
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.requets" /> 
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="request/actor/list.do"><spring:message code="master.page.list.all.request" /></a></li>
					<security:authorize access="hasRole('ANIMANIAC')">
						<li><a href="request/animaniac/list.do"><spring:message code="master.page.list.my.request" /></a></li>
						<li><a href="application/animaniac/list.do"><spring:message code="master.page.list.my.application" /></a></li>
					</security:authorize>
				</ul>
			</li>	
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li>
				<a class="fNiv" href="animaniac/register.do">
				<spring:message code="master.page.register" />
				</a>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/myProfile.do"><spring:message code="master.page.my.profile" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.folders" /></a></li>
					<security:authorize access="hasRole('ANIMANIAC')">
					<li><a href="searchEngine/animaniac/search.do"><spring:message code="master.page.searchEngine" /></a></li>
					</security:authorize>
					
					<security:authorize access="hasRole('VET')">
						
					</security:authorize>
					<security:authorize access="hasRole('ADMINISTRATOR')">
					<li><a href="type/administrator/list.do"><spring:message code="master.page.administrator.types" /></a></li>
					<li><a href="attribute/administrator/list.do"><spring:message code="master.page.administrator.attributes" /></a></li>
					<li><a href="partner/administrator/register.do"><spring:message code="master.page.register.partner" /></a></li>
					<li><a href="vet/administrator/register.do"><spring:message code="master.page.register.vet" /></a></li>
					<li><a href="dashboard/administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
					<li><a href="abuseReport/administrator/list.do"><spring:message code="master.page.administrator.abuseReport" /></a></li>
						<li><a href="spamword/administrator/list.do"><spring:message code="master.page.administrator.spamword" /></a></li>
					</security:authorize>
					<security:authorize access="hasRole('PARTNER')">
					<li><a href="banner/partner/list.do"><spring:message code="master.page.partner.banner" /></a></li>
						
					</security:authorize>
					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

