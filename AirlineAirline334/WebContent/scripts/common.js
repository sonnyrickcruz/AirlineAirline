/**
 * 
 */
$(document).ready(function(){
	
	$("#loginSubmitButton").on("click", function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var message;
		$.ajax({
			url : 'LoginActionJson',
			type : 'POST',
			data : {
			"username" : username,
			"password" : password
			},
			traditional: true,
			async : false,
			cache : false,
			success : function(data) {
				message = JSON.stringify(data).replace(/[^a-z0-9\s]/gi, '');
			},
			error : function(data) {
				alert("Error");
			}
		});
		if (message == "error"){
			$("p i").empty();
			$("p i").append("Error Message");
			return false;
		}
		
	})
});