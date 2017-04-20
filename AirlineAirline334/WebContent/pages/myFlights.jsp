<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container">
	<h1>
		<span class="glyphicon glyphicon-plane"></span> My Flights
	</h1>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#searchFlights">Search My Flights</a></li>
		<li><a data-toggle="tab" href="#currentFlights">Current Flights</a></li>
		<li><a data-toggle="tab" href="#futureFlights">Future Flights</a></li>
		<li><a data-toggle="tab" href="#flownFlights">Flown Flights</a></li>
	</ul>

	<div class="tab-content">
		<div id="searchFlights" class="tab-pane fade in active">
			<div class="container">
				<h3>Search Flights</h3>
				
				<form onsubmit="viewSearchResult()" class="text-center">
					<div class="form-group">
						<label for="origin">Route Origin: <input type="text" class="form-control" id="searchOrigin" name="origin" placeholder="e.g. Manila"></label>						
						<label class="control-label" for="departure">Date of Departure: 
        				<input class="form-control" id="departure" name="date" placeholder="MM/DD/YY" type="text" readonly/></label>
					</div>
					<div class="form-group">
						<label for="destination">Route Destination: <input type="text" class="form-control" id="searchDestination" name="destination" placeholder="e.g. Davao"></label>
						<label class="control-label" for="arrival">Date of Arrival: 
        				<input class="form-control" id="arrival" name="date" placeholder="MM/DD/YY" type="text" readonly/></label>
					</div>
					
					<input type="submit" class="btn btn-success" value="Search">
				</form>
				
				<div id="searchResultsMyFlights">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Status</th>
								<th>Book Id</th>
								<th>Flight Number</th>
								<th>Origin</th>
								<th>Destination</th>
								<th>Departure Date</th>
								<th>Departure Time</th>
								<th>Arrival Date</th>
								<th>Arrival Time</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator var="myAllFLights"
								value="#session.myAllFLightsList">
								<tr>
									<td>${ myAllFLights.flightBean.flightStatus }</td>
									<td>${ myAllFLights.bookId }</td>
									<td>AER - ${ myAllFLights.flightBean.flightId }${ myAllFLights.flightBean.route.routeId }</td>
									<td>${ myAllFLights.flightBean.route.origin.location }</td>
									<td>${ myAllFLights.flightBean.route.destination.location }</td>
									<td>${ myAllFLights.flightBean.departureDate }</td>
									<td>${ myAllFLights.flightBean.departureTime }</td>
									<td>${ myAllFLights.flightBean.arrivalDate }</td>
									<td>${ myAllFLights.flightBean.arrivalTime }</td>
									<td><button type="button" class="btn btn-default">View
											Details</button></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="currentFlights" class="tab-pane fade">
			<div class="container">
				<h3>Current Flights</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Status</th>
							<th>Book Id</th>
							<th>Flight Number</th>
							<th>Origin</th>
							<th>Destination</th>
							<th>Departure Date</th>
							<th>Departure Time</th>
							<th>Arrival Date</th>
							<th>Arrival Time</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="currentFlights"
							value="#session.currentFlightsList">
							<tr>
								<td>${ currentFlights.flightBean.flightStatus }</td>
								<td>${ currentFlights.bookId }</td>
								<td>AER - ${ currentFlights.flightBean.flightId }${ currentFlights.flightBean.route.routeId }</td>
								<td>${ currentFlights.flightBean.route.origin.location }</td>
								<td>${ currentFlights.flightBean.route.destination.location }</td>
								<td>${ currentFlights.flightBean.departureDate }</td>
								<td>${ currentFlights.flightBean.departureTime }</td>
								<td>${ currentFlights.flightBean.arrivalDate }</td>
								<td>${ currentFlights.flightBean.arrivalTime }</td>
								<td><button type="button" class="btn btn-default">View
										Details</button></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<div id="futureFlights" class="tab-pane fade">
			<div class="container">
				<h3>Future Flights</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Status</th>
							<th>Book Id</th>
							<th>Flight Number</th>
							<th>Origin</th>
							<th>Destination</th>
							<th>Departure Date</th>
							<th>Departure Time</th>
							<th>Arrival Date</th>
							<th>Arrival Time</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="futureFlights" value="#session.futureFlightsList">
							<tr>
								<td>${ futureFlights.flightBean.flightStatus }</td>
								<td>${ futureFlights.bookId }</td>
								<td>AER - ${ futureFlights.flightBean.flightId }${ futureFlights.flightBean.route.routeId }</td>
								<td>${ futureFlights.flightBean.route.origin.location }</td>
								<td>${ futureFlights.flightBean.route.destination.location }</td>
								<td>${ futureFlights.flightBean.departureDate }</td>
								<td>${ futureFlights.flightBean.departureTime }</td>
								<td>${ futureFlights.flightBean.arrivalDate }</td>
								<td>${ futureFlights.flightBean.arrivalTime }</td>
								<td><button type="button" class="btn btn-default">View
										Details</button></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<div id="flownFlights" class="tab-pane fade">
			<div class="container">
				<h3>Flown Flights</h3>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Status</th>
							<th>Book Id</th>
							<th>Flight Number</th>
							<th>Origin</th>
							<th>Destination</th>
							<th>Departure Date</th>
							<th>Departure Time</th>
							<th>Arrival Date</th>
							<th>Arrival Time</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="flownFlights" value="#session.flownFlightsList">
							<tr>
								<td>${ flownFlights.flightBean.flightStatus }</td>
								<td>${ flownFlights.bookId }</td>
								<td>AER - ${ flownFlights.flightBean.flightId }${ flownFlights.flightBean.route.routeId }</td>
								<td>${ flownFlights.flightBean.route.origin.location }</td>
								<td>${ flownFlights.flightBean.route.destination.location }</td>
								<td>${ flownFlights.flightBean.departureDate }</td>
								<td>${ flownFlights.flightBean.departureTime }</td>
								<td>${ flownFlights.flightBean.arrivalDate }</td>
								<td>${ flownFlights.flightBean.arrivalTime }</td>
								<td><button type="button" class="btn btn-default">View
										Details</button></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<script>
	//datepicker
	$(document).ready(function(){
      var date_input=$('input[name="date"]'); //our date input has the name "date"
      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
      var options={
        format: 'mm/dd/yy',
        container: container,
        todayHighlight: true,
        autoclose: true,
        changeMonth : true,
        changeYear : true,
        yearRange: '-100y:c+nn',
      };
      date_input.datepicker(options);
    });
    
	//search
    function viewSearchResult(event){    	
    	var searchOrigin = $("#searchOrigin").val();
    	var searchDestination = $("#searchDestination").val();
    	var departure = $("#departure").val();
    	var arrival = $("#arrival").val();
    	
    	$.ajax({
    		url : 'search-my-flights',
    		type : 'GET',
    		dataType : 'html',
    		data : { "searchOrigin" : searchOrigin, "searchDestination" : searchDestination, "departure" : departure, "arrival" : arrival },
    		async : false,
    		cache : false,
    		success : function(data) {
    			$('#searchResultsMyFlights').html(data);
    		}
    	});
    }
</script>