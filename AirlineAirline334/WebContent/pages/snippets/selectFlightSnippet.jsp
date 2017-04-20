<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
	<s:if test="%{flights.size > 0}">
		<form action="">
			<table>
				<thead>
					<tr>
						<th></th>
						<th>Departure</th>
						<th>Arrival</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="flight" value="flights">
						<tr>
							<td>
								<div class="radio">
									<label><input type="radio" name="optradio">Option 2</label>
								</div>
							</td>
							<td>${flight.departureTime}</td>
							<td>${flight.arrivalTime}</td>
							<td>${sessionScope.ticket.flight.route.price}</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</form>
	</s:if>
	<s:else>
		No flight in this date
	</s:else>
</div>