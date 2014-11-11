<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>My Saved Searches</h1>

${message}

<c:forEach items="${searches}" var="search">
		<div class="panel panel-primary" onclick="javascript:location.href='search?searchId=${search.id}'">
			<div class="panel-heading"><h5></h5></div>
			<div class="panel-body" >
				Minimal Price: ${search.priceMin}<br>
				Maximal Price: ${search.priceMax}<br>
				Minimal Room Size: ${search.roomSizeMin}<br>
				Maximal Room Size: ${search.roomSizeMax}<br>
				City: ${search.city}<br>
			</div>
			<div class="panel-footer">
				<a class="btn btn-primary" href="removeSearch?id=${search.id}">Remove Search</a>
			</div>
		</div>
</c:forEach>

<c:import url="template/footer.jsp" />