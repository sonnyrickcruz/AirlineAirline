<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="originSession" value="%{'(' + #session.ticket.flight.route.origin.airportId + ') ' + #session.ticket.flight.route.origin.location}"/>
<s:set var="destinationSession" value="%{'(' + #session.ticket.flight.route.destination.airportId + ') ' + #session.ticket.flight.route.destination.location}"/>
<s:set var="departureSession" value="%{#session.ticket.flight.departureDate}"/>
<s:set var="paxSession" value="%{#session.ticket.flight.route.pax}"/>
<div>
	<form action="search-flights-submit" method="POST">
	  	<div class="form-group">
		    <label for="origin"> Origin: </label>
		    <input type="text" class="form-control" id="searchOrigin" name="searchOrigin" value="${originSession}">
	  	</div>
	  	<div class="form-group">
		    <label for="destination"> Destination: </label>
		    <input type="text" class="form-control" id="searchDestination" name="searchDestination" value="${destinationSession}">
		    <!-- <input type="text" id="destinationDate" class="date-picker"> -->
	  	</div>
	    <div class="form-group">
	        <label for="departureDate" class="control-label"> Departure Date: </label>
	        <div class="controls">
	            <div class="input-group">
	                <input id="departureDate" type="text" class="date-picker form-control" name="searchDepartureDate" value="${departureSession}"/>
	                <label for="departureDate" class="input-group-addon btn"><span class="glyphicon glyphicon-calendar"></span></label>
	            </div>
	        </div>
	    </div>
	    <div class="form-group">
	    	<label for="noOfPax"> Passengers: </label>
		    <select class="form-control" id="noOfPax" name="noOfPax">
			    <s:iterator status="rowStatus" value='{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}'>
					<option <s:if test="%{#rowStatus.count == #paxSession}"> selected </s:if>><s:property value="%{#rowStatus.count}"/></option>
				</s:iterator>
		    </select>
	    </div>
	 	<button type="submit" class="btn btn-default"> Search Flights </button>
	</form>
</div>