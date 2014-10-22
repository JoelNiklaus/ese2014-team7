<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

<h1>Ads</h1>
<c:forEach items="${adView}" var="ad">
    <div class="main">
    	<table class="smalltable">
    		<tr>
    			<td><legend>Title</legend>
    			<td><legend>${ad.title}</legend>
    		</tr>
    		<tr>
    			<td>Street&nbsp;&nbsp;
    			<td>${ad.street}
    		</tr>
    		<tr>
    			<td>House Nr.&nbsp;&nbsp;
    			<td>${ad.houseNr}
    		</tr>
    		<tr>
    			<td>City&nbsp;&nbsp;
    			<td>${ad.city}
    		</tr>
    		<tr>
    			<td>ZIP&nbsp;&nbsp;
    			<td>${ad.zip}
    		</tr>
    		<tr>
    			<td>Rent&nbsp;&nbsp;
    			<td>${ad.rent}
    		</tr>
    		<tr>
    			<td>Additional Cost&nbsp;&nbsp;
    			<td>${ad.addCost}
    		</tr>
    		<tr>
    			<td>Move In Date&nbsp;&nbsp;
    			<td>${ad.dateIn}
    		</tr>
    		<tr>
    			<td>Move Out Date&nbsp;&nbsp;
    			<td>${ad.dateOut}
    		</tr>
    		<tr>
    			<td>Type&nbsp;&nbsp;
    			<td>${ad.type}
    		</tr>
    		<tr>
    			<td>Room Size&nbsp;&nbsp;
    			<td>${ad.roomSize}
    		</tr>
    		<tr>
    			<td>Description&nbsp;&nbsp;
    			<td>${ad.description}
    		</tr>
    		<tr>
    			<td>About Us&nbsp;&nbsp;
    			<td>${ad.us}
    		</tr>
    		<tr>
    			<td>Ideal Roomie&nbsp;&nbsp;
    			<td>${ad.you}
    		</tr>
    	</table>
    </div>	
</c:forEach>



	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
