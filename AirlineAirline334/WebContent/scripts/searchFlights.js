var origin;
var originList = {};
var destination;
var destinationList = {};
var autocompleteList = [];
var routes;

$(document).ready(function(){
	initRoutes();
	setAutoCompleteOrigin();
	setAutoCompleteDestination();
	$(".date-picker").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: 0,
        dateFormat: "mm/dd/yy"
	});
	$(document).on("blur", "#searchOrigin", function(){
		origin = $(this).val();
		var exists = $.inArray(origin,autocompleteList);
		if(exists < 0) {
			$(this).val("");
			$("#searchDestination").val("");
			//$(this).select();
		}
	});
	$(document).on("blur", "#searchDestination", function(){
		var destination = $(this).val();
		var exists = $.inArray(destination,autocompleteList);
		if(exists < 0) {
			$(this).val("");
			$("#searchOrigin").select();
		} 
	});
});

//Setting autocomplete items
function setAutoCompleteOrigin (){
	$("#searchOrigin").autocomplete({
		source: function(request, response) {
			var term = request.term.toLowerCase();
			var keys = $.map(originList, function(element,index) {return index});
			var result = [];
			$.each(keys, function(key, value) {
				var isInString = (value.toLowerCase()).replace(/[_\W]+/g, "").search((term.toLowerCase()).replace(/[_\W]+/g, ""));
				if (isInString > 0) {
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
			var resultList = []
			var option;
			var originVal;
			$.each(routes, function(key, value) {
				originVal = "(" + value.origin.airportId + ") " + value.origin.location
				if (originVal == origin) {
					option = "(" + value.destination.airportId + ") " + value.destination.location
					var str = option.toLowerCase(); 
					var isInString = str.search(term);
					if (isInString > 0 && jQuery.inArray(option, resultList) == -1) {
						result.push({
							label: option,
							value: option
						})
						resultList.push(option);
						autocompleteList = resultList;
					}
				}
			});
			resultList = []
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
			originList[originKey] = [destinationVal]
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
			destinationList[destinationKey] = [originVal]
		}
	});
}

function removeSymbols(string) {
	return (string.toLowerCase()).replace(/[_\W]+/g, "");
}