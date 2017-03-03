<div>
	<form>
		<div class="form-group">
			<label for="origin">Origin:</label> <input type="text"
				class="form-control" id="searchOrigin">
			<!-- <input type="text" id="originDate" class="date-picker"> -->
		</div>
		<div class="form-group">
			<label for="destination">Destination:</label> <input type="text"
				class="form-control" id="searchDestination">
			<!-- <input type="text" id="destinationDate" class="date-picker"> -->
		</div>
		<button type="button" class="btn btn-default" id="searchRoute"
			data-toggle="modal" data-target="#myModal">Search Route</button>
	</form>
	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add A Flight</h4>
				</div>
				<div class="modal-body">
					<div class="well well-sm">
						<span class="glyphicon glyphicon-road" id="routeInfo"></span>
					</div>
					<form class="form-horizontal" action="create-flight" method="POST">
						<div class="form-group">
							<div class="col-sm-10">
								<input class="form-control" id="travelTime" type="hidden" name="travelTime">
								<input class="form-control" id="routeId" type="hidden" name="routeId">
								<label class="control-label">Airplane</label> <input
									class="form-control" id="selectAirplane" type="text" name="selectAirplane">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-10">
								<label for="departureDate" class="control-label">
									Departure Date: </label>
								<div class="controls">
									<div class="input-group">
										<input id="departureDate" type="text"
											class="date-picker form-control" name="date"
											value="${departureSession}" /> <label for="departureDate"
											class="input-group-addon btn"><span
											class="glyphicon glyphicon-calendar"></span></label>
									</div>
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-default"> Search Flights </button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
</div>