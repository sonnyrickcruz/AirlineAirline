<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set var="originSession" value="%{'(' + #session.ticket.flight.route.origin.airportId + ') ' + #session.ticket.flight.route.origin.location}"/>
<s:set var="destinationSession" value="%{'(' + #session.ticket.flight.route.destination.airportId + ') ' + #session.ticket.flight.route.destination.location}"/>
<s:set var="departureSession" value="%{#session.ticket.flight.departureDate}"/>
<s:set var="paxSession" value="%{#session.ticket.flight.route.pax}"/>
<s:set var="route" value="%{#session.ticket.flight.route.routeId}"/>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img src="./images/airplane1.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Quote of the day!</h3>
				<p>When everything seems to be going against you, remember that
					the airplane takes off against the wind, not with it.</p>
				<p class="quoteAuthor">- Henry Ford</p>
			</div>
		</div>
		<div class="item">
			<img src="./images/airplane3.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Discount!</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Quisque eu semper est. Phasellus vehicula pretium commodo. Duis in
					ullamcorper enim. Mauris venenatis porta elit id malesuada.
					Pellentesque eu venenatis sem. Aliquam erat volutpat. Pellentesque
					hendrerit turpis nec quam venenatis, et luctus leo sodales. Cras id
					orci efficitur, blandit nibh a, lacinia purus.</p>
			</div>
		</div>
		<div class="item">
			<img src="./images/airplane2.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Promo!</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Quisque eu semper est. Phasellus vehicula pretium commodo. Duis in
					ullamcorper enim. Mauris venenatis porta elit id malesuada.
					Pellentesque eu venenatis sem. Aliquam erat volutpat. Pellentesque
					hendrerit turpis nec quam venenatis, et luctus leo sodales. Cras id
					orci efficitur, blandit nibh a, lacinia purus.</p>
			</div>
		</div>
		<div class="item">
			<img src="./images/airplane4.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Advertisement!</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Quisque eu semper est. Phasellus vehicula pretium commodo. Duis in
					ullamcorper enim. Mauris venenatis porta elit id malesuada.
					Pellentesque eu venenatis sem. Aliquam erat volutpat. Pellentesque
					hendrerit turpis nec quam venenatis, et luctus leo sodales. Cras id
					orci efficitur, blandit nibh a, lacinia purus.</p>
			</div>
		</div>

		<div class="right carousel-control">
			<div id="bookingWrapper">
				<form action="search-flights-submit" method="POST">
					<input type="hidden" id="route" name="route" value="${route}">
					<div class="form-group">
						<label for="origin"> Origin: </label> <input type="text" class="form-control" id="searchOrigin" name="searchOrigin" value="${originSession}">
					</div>
					<div class="form-group">
						<label for="destination"> Destination: </label> <input type="text" class="form-control" id="searchDestination" name="searchDestination" value="${destinationSession}">
						<!-- <input type="text" id="destinationDate" class="date-picker"> -->
					</div>
					<div class="form-group">
						<label for="departureDate" class="control-label">
							Departure Date: </label>
						<div class="controls">
							<div class="input-group">
								<input id="departureDate" type="text" class="date-picker form-control" name="searchDepartureDate" value="${departureSession}" /> 
								<label for="departureDate" class="input-group-addon btn">
									<span class="glyphicon glyphicon-calendar"></span>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="noOfPax"> Passengers: </label> <select
							class="form-control" id="noOfPax" name="noOfPax">
							<s:iterator status="rowStatus"
								value='{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}'>
								<option <s:if test="%{#rowStatus.count == #paxSession}"> selected </s:if>>
									<s:property value="%{#rowStatus.count}" />
								</option>
							</s:iterator>
						</select>
					</div>
					<button type="submit" id="searchFlight" class="btn btn-default"> Search for Flights</button>
				</form>
			</div>
		</div>
	</div>

</div>
<form id="airplaneForm" action="aircraftAction" method="POST">
	<button type="submit">AIRPLANE</button>
</form>
<form id="airportForm" action="airportAction" method="POST">
	<button type="submit">AIRPORT</button>
</form>
<a type="button" class="btn btn-success btn-block" href="add-ons">Addons</a>