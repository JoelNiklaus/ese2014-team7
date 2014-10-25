<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:import url="template/header.jsp" />

	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="/Skeleton/lib/noUiSlider/jquery.nouislider.js"></script>
	<script src="/Skeleton/lib/noUiSlider/jquery.liblink.js"></script>
	<script src="/Skeleton/lib/noUiSlider/wNumb.js"></script>
	<link href="/Skeleton/lib/noUiSlider/jquery.nouislider.css" rel="stylesheet">

	<style>
		.priceSliderTooltip {
			display: block;
			position: absolute;
		 	border: 1px solid #D9D9D9;
			font: 400 12px/12px Arial;
			border-radius: 3px;
			background: #fff;
		
			top: -40px;
			left: -20px;
			padding: 5px;

			text-align: center;
			width: 70px;
		}
		.priceSliderTooltip strong {
			display: block;
			padding: 2px;
	  	}
	  	
	  	.roomSizeSliderTooltip {
			display: block;
			position: absolute;
		 	border: 1px solid #D9D9D9;
			font: 400 12px/12px Arial;
			border-radius: 3px;
			background: #fff;
			top: -40px;
			padding: 5px;
			left: -20px;
			text-align: center;
			width: 70px;
		}
		.roomSizeSliderTooltip strong {
			display: block;
			padding: 2px;
	  	}
	</style>

	<h2 class="form-heading">Search</h2>
	<br />
	<br />
	<form:form method="post" modelAttribute="searchForm" action="search" id="searchForm" cssClass="form-horizontal" autocomplete="off">
		<fieldset>
			<div class="row">
				<div class="col-md-2">
					<label for="field-price">Price</label>
				</div>
				<div class="col-md-3" id="priceSlider"></div>
				<div class="col-md-1"></div>
				<div class="col-md-2">
					<label for="field-rooms">Room Size</label>
				</div>
				<div class="col-md-3" id="roomSizeSlider"></div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2">
					<label for="field-city">City</label>
				</div>
				<form:input path="city" id="field-city" tabindex="5" maxlength="35"/>
			</div>
	
			<form:input type="hidden" path="priceMin" id="field-priceMin" maxlength="45"/>
			<form:input type="hidden" path="priceMax" id="field-priceMax" maxlength="45"/>
			<form:input type="hidden" path="roomSizeMin" id="field-roomSizeMin" maxlength="45"/>
			<form:input type="hidden" path="roomSizeMax" id="field-roomSizeMax" maxlength="45"/>
	
			<div class="form-actions">
				<button type="button" class="btn">Save</button>
				<button type="submit" class="btn btn-primary">Search</button>
			</div>
		</fieldset>
	</form:form>
		
	<br>

	

	<c:forEach items="${searchResults}" var="ad">
		<div class="panel panel-primary" onclick="javascript:location.href='ad?id=${ad.id}'">
		
			<div class="panel-heading"><h5>${ad.title}</h5></div>
			<div class="panel-body" >
				<a class="pull-left" >
		    		<img class="media-object" src="/Skeleton/img/house1.jpeg" height="100px">
		  		</a>
		  		<p>${ad.description}</p>
				
			</div>
				
			<div class="panel-footer"><b>Price:</b> CHF ${ad.rent}  <b>Room Size:</b> ${ad.roomSize}m²</div>
		</div>
	</c:forEach>
		
	<script>
	/**
	 * Search Sliders 
	 *
	 */
		$("#priceSlider").noUiSlider({
			start: [0, 3000],
			behaviour: 'drag-tap',
			connect: true,
			step: 10,
			range: ({
				'min': 0,
				'max': 3000
			}),
			/*format: wNumb({
				decimals: 0,
				postfix: ' CHF',
			})*/
		});
		$("#priceSlider").Link('lower').to($("#field-priceMin"), null, wNumb({
					decimals: 0,
				}));


		$("#priceSlider").Link('lower').to('-inline-<div class="priceSliderTooltip" ></div>', function ( value ) {
			
			$(this).html(
				'<strong>Min: </strong>' +
				'<span>' + value + '</span>'
			);
		});
	
		$("#priceSlider").Link('lower').to('-inline-<div class="priceSliderTooltip" ></div>', function ( value ) {
			
			$(this).html(
				'<strong>Min: </strong>' +
				'<span>' + value + '</span>'
			);
		});
		$("#priceSlider").Link('upper').to($("#field-priceMax"), null, wNumb({
			decimals: 0,
		}));
		$("#priceSlider").Link('upper').to('-inline-<div class="priceSliderTooltip" ></div>', function ( value ) {
		

			if(value==3000)
			{
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + "Unlimited" + '</span>'
					);
				
			} else {
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + value + '</span>'
					);
			}

		});
		$("#roomSizeSlider").noUiSlider({
			start: [0, 300],
			behaviour: 'drag-tap',
			connect: true,
			step: 1,
			range: ({
				'min': 0,
				'max': 300
			}),
			/*format: wNumb({
				decimals: 0,
				postfix: ' CHF',
			})*/
		});
		
		$("#roomSizeSlider").Link('lower').to($("#field-roomSizeMin"), null, wNumb({
			decimals: 0,
		}));
		$("#roomSizeSlider").Link('lower').to('-inline-<div class="roomSizeSliderTooltip" ></div>', function ( value ) {
		
			$(this).html(
				'<strong>Min: </strong>' +
				'<span>' + value + '</span>'
			);
		});
		$("#roomSizeSlider").Link('upper').to($("#field-roomSizeMax"), null, wNumb({
			decimals: 0,
		}));
		$("#roomSizeSlider").Link('upper').to('-inline-<div class="roomSizeSliderTooltip" ></div>', function ( value ) {
		

			if(value==300)
			{
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + "Unlimited" + '</span>'
					);
				
			} else {
				$(this).html(
						'<strong>Max: </strong>' +
						'<span>' + value + '</span>'
					);
			}

		});
	</script>		

	 	

	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>


<c:import url="template/footer.jsp" />
