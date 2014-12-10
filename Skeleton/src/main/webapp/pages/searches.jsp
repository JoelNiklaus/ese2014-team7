<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>My Saved Searches</h1>

<c:if test="${empty searches}">
	<div class="jumbotron">
		<p>
			You have not saved any searches yet. If you want to find apartments, studios, houses etc, you can here <a
				href="search">search ads</a>. To help you remember your search criteria you can save your search under "advanced
			Search".
		</p>
	</div>
</c:if>

<c:if test="${not empty message}">
	<div class="alert alert-success" role="alert">${message}</div>
</c:if>

<c:forEach items="${searches}" var="search">
	<div class="panel panel-primary" onclick="javascript:location.href='search?searchId=${search.id}'">
		<div class="panel-heading">
			<h5></h5>
		</div>
		<div class="panel-body">
			Minimal Price: ${search.priceMin}<br> Maximal Price: ${search.priceMax}<br> Minimal Room Size:
			${search.roomSizeMin}<br> Maximal Room Size: ${search.roomSizeMax}<br> City: ${search.city}<br>
			Earliest Move In Date: ${search.earliestMoveInDate}<br> Latest Move In Date: ${search.latestMoveInDate}<br>
		</div>
		<div class="panel-footer">
			<a class="btn btn-primary" href='search?searchId=${search.id}'>
				<span class="glyphicon glyphicon-search"></span> Search
			</a>
			<a class="btn btn-danger btn-xs pull-right" href="removeSearch?id=${search.id}"><span
				class="glyphicon glyphicon-remove"></span>delete</a>
		</div>
	</div>
</c:forEach>

<c:import url="template/footer.jsp" />