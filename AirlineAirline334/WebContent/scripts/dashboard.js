var origin = $("#searchOrigin").val();
var originList = {};
var destination = $("#searchDestination").val();
var destinationList = {};
var autocompleteList = [];
var routes;

$(document).ready(function(){
	$('.carousel').carousel({
	    pause: "false"
	});
	init();
	$(".date-picker").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: 0,
        dateFormat: "mm/dd/yy"
	});
	$(document).on("blur", "#searchOrigin", function(){
		origin = $(this).val();
		var keys = $.map(originList, function(element,index) {return index});
		var exists = $.inArray(origin,keys);
		if(exists < 0) {
			origin = "";
			$(this).val("");
		}
	});
	$(document).on("blur", "#searchDestination", function(){
		destination = $(this).val();
		var keys = $.map(destinationList, function(element,index) {return index});
		var exists = $.inArray(destination,keys);
		if(exists < 0) {
			destination = "";
			$(this).val("");
		} 
	});
	$(document).on("click", "#searchFlight", function() {
		if (origin != null && origin.length > 0 && destination != null && origin.length > 0) {
			var originString;
			var destinationString;
			$.each(routes, function(key, value) {
				originString = "(" + value.origin.airportId + ") " + value.origin.location;
				destinationString = "(" + value.destination.airportId + ") " + value.destination.location;
				if (originString == origin && destinationString == destination) {
					$("#route").val(value.routeId);
					return;
				}
			});
		}
	});
})

function init() {
	origin = $("#searchOrigin").val();
	destination = $("#searchDestination").val();
	initRoutes();
	setAutoCompleteOrigin();
	setAutoCompleteDestination();
}

//Setting autocomplete items
function setAutoCompleteOrigin (){
	$("#searchOrigin").autocomplete({
		source: function(request, response) {
			var term = request.term.toLowerCase();
			var result = [];
			var keys;
			if (destination != null && destination.length > 0) {
				keys = destinationList[destination];
			} else {
				keys = $.map(originList, function(element,index) {return index});
			}
			$.each(keys, function(key, value) {
				var isInString = removeSymbols(value).search(removeSymbols(term));
				if (isInString >= 0) {
					result.push(value);
				}
			});
			response(result);
	    },minLength:0
	}).bind('focus', function(){ $(this).autocomplete("search"); } );
}

//Setting autocomplete items
function setAutoCompleteDestination(){
	$("#searchDestination").autocomplete({
		source: function(request, response) {
			var term = request.term.toLowerCase();
			var result = [];
			var keys;
			if (origin != null && origin.length > 0) {
				keys = originList[origin];
			} else {
				keys = $.map(destinationList, function(element,index) {return index});
			}
			$.each(keys, function(key, value) {
				var isInString = removeSymbols(value).search(removeSymbols(term));
				if (isInString >= 0) {
					result.push(value);
				}
			});
			response(result);
	    },minLength:0
	}).bind('focus', function(){ $(this).autocomplete("search"); } );
}

function initRoutes() {
	$.ajax({
        url: "search-flights-routes",
        type: "POST",
        dataType: "json",
		async: false,
		cache: true,
        messages: {
            noResults: ''
        },
        success: function(data) {
        	routes = data;
        	initOrigins();
        	initDestinations();
        }
	});
}

function initOrigins() {
	var originKey = '';
	var destinationVal = '';
	$.each(routes, function(key, value) {
		originKey = "(" + value.origin.airportId + ") " + value.origin.location;
		destinationVal = "(" + value.destination.airportId + ") " + value.destination.location;
		if (originList[originKey] != null) {
			originList[originKey].push(destinationVal);
		} else {
			originList[originKey] = [destinationVal];
		}
	});
}

function initDestinations() {
	var destinationKey = '';
	var originVal = '';
	$.each(routes, function(key, value) {
		destinationKey = "(" + value.origin.airportId + ") " + value.origin.location;
		originVal = "(" + value.destination.airportId + ") " + value.destination.location;
		if (destinationList[destinationKey] != null) {
			destinationList[destinationKey].push(originVal);
		} else {
			destinationList[destinationKey] = [originVal];
		}
	});
}

function removeSymbols(string) {
	return (string.toLowerCase()).replace(/[_\W]+/g, "");
}