var origin;
var routeId;
$(document).ready(function() {
	setAutoComplete("searchOrigin");
	setAutoComplete("searchDestination");
	$(".date-picker").datepicker({
		changeMonth : true,
		changeYear : true,
		minDate : 0
	});
	$(document).on("blur", "#searchOrigin", function() {
		origin = $(this).val();
	});
	searchRoute();
	autoComplete("selectAirplane");
});

// Setting autocomplete items
function setAutoComplete(action) {
	$("#" + action).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "add-flights-autocomplete",
				type : "POST",
				dataType : "json",
				async : false,
				cache : true,
				data : {
					term : request.term,
					action : action,
					origin : origin
				},
				success : function(data) {
					if (data.length > 0) {
						response(data);
						console.log(data);
					} else {
						response([ "No Results Found" ]);
					}
				}
			});
		},
		minLength : 0
	}).bind('focus', function() {
		$(this).autocomplete("search");
	});
}
function searchRoute() {

	$("#searchRoute").on('click', function() {
		var selectedOrigin = $("#searchOrigin").val();
		var selectedDestination = $("#searchDestination").val();
		var routeInfo = selectedOrigin + " " + selectedDestination
		$.ajax({
			url : "search-route",
			type : "POST",
			async : false,
			cache : true,
			data : {
				origin : selectedOrigin,
				destination : selectedDestination
			},
			success : function(data) {
				var regex = /[0-9]+\s*}\s*/;
				var input = data
				if (regex.test(input)) {
					var travelTime = input.match(regex);

				} else {
					alert("No matches found!");
				}
				regex = /[0-9]+\s*"\s*/;
				if (regex.test(input)) {
					var routeNumber = input.match(regex);
					
				} else {
					alert("No matches found!");
				}
				$("#travelTime").val(travelTime);
				$("#routeId").val(routeNumber);
				/*
				 * $(document).on("blur", "#routeId", function() { routeId =
				 * $(this).val(); });
				 */
				$.each($('input'), function(i, val) {
					if ($(this).attr("type") == "hidden") {
						routeId = $("#routeId").val();
						travelDuration = $("#travelTime").val();
					}
				});
			},
			error : function(data) {
				alert("ERROR!")
			}
		});

	});
}
function autoComplete(action) {
	$("#" + action).autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "select-airplane-autocomplete",
				type : "POST",
				dataType : "json",
				async : false,
				cache : true,
				data : {
					action : action,
					routeId : routeId
				},
				success : function(data) {
					if (data.length > 0) {
						response(data);
						console.log(data);
					} else {
						response([ "No Results Found" ]);
					}
				}
			});
		},
		minLength : 0
	}).bind('focus', function() {
		$(this).autocomplete("search");
	});
}