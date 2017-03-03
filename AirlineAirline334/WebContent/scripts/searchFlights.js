var origin;
var autocompleteList;

$(document).ready(function(){
	setAutoComplete("searchOrigin");
	setAutoComplete("searchDestination");
	origin = $("#searchOrigin").val();
	$(".date-picker").datepicker({
        changeMonth: true,
        changeYear: true,
        minDate: 0,
        dateFormat: "mm/dd/yy"
	});
	$(document).on("blur", "#searchOrigin", function(){
		origin = $(this).val();
		exists = $.inArray(origin,autocompleteList);
		if(exists < 0) {
			$(this).val("");
			$("#searchDestination").val("");
			//$(this).select();
		} 
	});
	$(document).on("blur", "#searchDestination", function(){
		destination = $(this).val();
		exists = $.inArray(destination,autocompleteList);
		if(exists < 0) {
			$(this).val("");
			$("#searchOrigin").select();
		} 
	});
});

//Setting autocomplete items
function setAutoComplete (action){
	$("#" + action).autocomplete({
		source: function( request, response ) {
			$.ajax({
		        url: "search-flights-autocomplete",
		        type: "POST",
		        dataType: "json",
				async: false,
				cache: true,
	            messages: {
	                noResults: ''
	            },
	            data: {
		        	term: request.term,
		        	action: action,
		        	origin: origin
		        },
		        success: function(data) {
			        response(data);
		        	autocompleteList = data;
		        }
			});
	    },minLength:0
	}).bind('focus', function(){ $(this).autocomplete("search"); } );
}