<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<h1>My Saved Searches</h1>

${message}

<c:forEach items="${searches}" var="search">
	<p>
		Id: ${search.id}<br>
		Minimal Price: ${search.priceMin}<br>
		Maximal Price: ${search.priceMax}<br>
		Minimal Room Size: ${search.roomSizeMin}<br>
		Maximal Room Size: ${search.roomSizeMax}<br>
		City: ${search.city}<br>
		<a class="btn btn-primary" href="removeSearch?id=${search.id}">remove Search</a>
	</p>
</c:forEach>

<c:import url="template/footer.jsp" />