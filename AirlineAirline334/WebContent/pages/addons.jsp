<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<body data-spy="scroll" data-target="#myScrollspy" data-offset="15">
	<div>
		<div>
			<h2>Addons</h2>
		</div>

		<div class="container-fluid">
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<div id="mealTab" class="container-fluid">
						<div>
							<div class="col-md-10">
								<h3>Meal</h3>
							</div>
							<div class="col-md-2">
								<a type="button" class="btn btn-success btn-block all-meal"
									data-toggle="modal" data-target="#myModal">One for all</a>
							</div>
						</div>
						<s:iterator value="%{passengerList}" var="passenger"
							status="counter">
							<div class="col-md-4">
								<div class="panel panel-info panel-user">
									<div class="panel-heading">
										<strong>${passenger.prefix }. ${passenger.firstName }
											${passenger.lastName }</strong>
									</div>
									<div class="panel-body">
										<img id="passengerMeal${ counter.index }" data-toggle="modal"
											data-target="#myModal" src="./images/meals/no-food-yet.jpg"
											class="img-thumbnail mealFrame" alt="None"> <input
											type="hidden" value="0" />
										<p id="passengerMealName${ counter.index }">
											<label class="reminder">*Click the image to add.</label>
										</p>
									</div>
								</div>
							</div>
						</s:iterator>

						<div id="myModal" class="modal fade" role="dialog">
							<div class="modal-dialog modal-lg">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Meal Selection</h4>
									</div>
									<div class="modal-body container-fluid">
										<s:iterator value="%{mealList}" var="meal"
											status="mealCounter">
											<div class="col-md-4">
												<div class="panel panel-info">
													<div class="panel-heading">
														<strong id="mealName${mealCounter.index}">${meal.name}</strong>
													</div>
													<div class="panel-body">
														<img id="mealChoice${mealCounter.index }"
															src="${meal.imgURI}" class="img-thumbnail mealFrameMenu"
															data-dismiss="modal" alt="${meal.name}">
														<p id="mealDescription${mealCounter.index}">${meal.description }</p>
													</div>
												</div>
											</div>
										</s:iterator>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div id="baggageTab">
						<div>
							<div class="col-md-10">
								<h3>Baggage</h3>
							</div>
							<div class="col-md-2">
								<a type="button" class="btn btn-success btn-block all-meal"
									data-toggle="modal" data-target="#baggageModal">One for all</a>
							</div>
						</div>
						<div class="container-fluid">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Firstname</th>
										<th>Lastname</th>
										<th>Baggage</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="%{passengerList}" var="passenger"
										status="counter">

										<tr>
											<td>${passenger.prefix }.${passenger.firstName }</td>
											<td>${passenger.lastName }</td>
											<td>
												<div id="passengerBaggage${counter.index}">
													<div class="btn-group" data-toggle="buttons">
														<label class="btn btn-info individualBaggage0 active">
															<input type="radio" name="options" value="0">
															None
														</label> <label class="btn btn-info individualBaggage1"> <input
															type="radio" name="options" value="1">10 KG
														</label> <label class="btn btn-info individualBaggage2"> <input
															type="radio" name="options" value="2"> 20 KG
														</label>
													</div>
												</div>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
						<div id="baggageModal" class="modal fade" role="dialog">
							<div class="modal-dialog modal-sm">

								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Baggage Selection</h4>
									</div>
									<div class="modal-body container-fluid">
										<div id="baggageButtonModal" class="btn-group"
											data-toggle="buttons" data-dismiss="modal">
											<label class="sample btn btn-info active"> <input
												value="0" type="radio" name="options" class="baggage-button" />
												None
											</label><label class="sample btn btn-info"> <input value="1"
												type="radio" name="options" class="baggage-button" /> 10 KG
											</label> <label class="sample btn btn-info"><input value="2"
												type="radio" name="options" class="baggage-button" /> 20 KG
											</label>
										</div>
									</div>
									<div class="modal-footer">
										<div class="footer-message">
											<h5>Note:</h5>
											kajskldfjalksjfasjdfljasldfjaksl31%31%31%
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div id="insuranceTab">
						<div>
							<div class="col-md-10">
								<h3>Insurance</h3>
							</div>
							<div class="col-md-2">
								<a type="button" class="btn btn-success btn-block all-meal"
									data-toggle="modal" data-target="#insuranceModal">One for all</a>
							</div>
						</div>
						<div class="container-fluid">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>Firstname</th>
										<th>Lastname</th>
										<th>Availed</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="%{passengerList}" var="passenger"
										status="counter">

										<tr>
											<td>${passenger.prefix }.${passenger.firstName }</td>
											<td>${passenger.lastName }</td>
											<td>
												<div id="passengerInsurance${counter.index}">
													<div class="btn-group" data-toggle="buttons">
														<label class="btn btn-info active individualInsurance1"> <input
															type="radio" name="options" id="option1"
															autocomplete="off" value="1"> Yes
														</label> <label class="btn btn-info individualInsurance0"> <input type="radio"
															name="options" id="option2" autocomplete="off" value="0"> No
														</label>
													</div>
												</div>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>

					</div>
					<div id="insuranceModal" class="modal fade" role="dialog">
						<div class="modal-dialog modal-sm">

							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Insurance Selection</h4>
								</div>
								<div class="modal-body container-fluid">
									<div id="insuranceButtonModal" class="btn-group"
										data-toggle="buttons" data-dismiss="modal">
										<label class="insuranceLabel btn btn-info active"> <input
											type="radio" name="options" id="option1" autocomplete="off"
											value="1"> Yes
										</label> <label class="insuranceLabel btn btn-info"> <input type="radio"
											name="options" id="option2" autocomplete="off" value="0"> No
										</label>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>

						</div>
					</div>
					<a type="button" id="submitAddons"
						class="btn btn-success btn-block">Submit Addons</a>
				</div>
			</div>
		</div>
	</div>

</body>


