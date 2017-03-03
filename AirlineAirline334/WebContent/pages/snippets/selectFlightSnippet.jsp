<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<form action="">
		<s:iterator var="flight" value="flights">
			${flight }
			<!-- FlightBean [flightStatus=null, flightId=639, route=null, airplaneId=123, departureDate=2016-11-04, departureTime=09:00:00, arrivalDate=2016-11-04, arrivalTime=10:30:00] -->
			<span>${flight.departureTime}</span> -> <span>${flight.arrivalTime}</span>
			<span>Price ${sessionScope.ticket.flight.route.price}</span>
			<br/>
		</s:iterator>
	</form>
</div>