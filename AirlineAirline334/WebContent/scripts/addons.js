$(document).ready(function(){
	$('#nextMeal').on('click',function(){
		$('#menu1').click();
	});
	var parentId;
	$('.mealFrame').on('click',function(){
		parentId = $(this).attr('id');
	});
	$('.all-meal').on('click',function(){
		parentId = "One for all";
	});
	$('.mealFrameMenu').on('click',function(){
		var idName = $(this).attr('id');
		var index = parentId.replace(/[^0-9]/g, '');
		var foodIndex = idName.replace(/[^0-9]/g, '')
		var mealName = $('#mealName'+foodIndex).text();
		var mealURI = $('#mealChoice'+foodIndex).attr('src');
		
		if (parentId == "One for all"){
			changeAttributeAll(idName,mealURI,mealName,foodIndex);
		} else{
			changeAttribute(idName,index,mealURI,mealName,foodIndex);
		}
	});
	var selectedBaggage;
	
	$(".insuranceLabel").on('click',function(event) {
		var test = $(this).find('input:radio').is(":checked");
		if(test == false){
			var value = $(this).find('input:radio').attr("value");
			for (var i = 0; i < 3; i++) {
				$('.individualInsurance'+i).removeClass('active');
			}
			counter = 0;
			while($('#passengerInsurance'+counter).length != 0){
				$('#passengerInsurance'+counter).children().children('.individualInsurance'+value).addClass('active');
				counter = counter +1;
			}
			console.log(value);
		}
		});
	$(".sample").on('click',function(event) {
		var test = $(this).find('input:radio').is(":checked");
		if(test == false){
			var value = $(this).find('input:radio').attr("value");
			for (var i = 0; i < 3; i++) {
				$('.individualBaggage'+i).removeClass('active');
			}
			counter = 0;
			while($('#passengerBaggage'+counter).length != 0){
				$('#passengerBaggage'+counter).children().children('.individualBaggage'+value).addClass('active');
				counter = counter +1;
			}
			console.log(value);
		}
		});
	$("#submitAddons").on('click',function(){
			counter =0;
			var mealArray = [];
			var baggageArray = [];
			var insuranceArray = [];
			while($('#passengerBaggage'+counter).length != 0){
				var baggageChoice = $('#passengerBaggage'+counter).children().children('.active').find('input:radio').attr("value");
				var insuranceChoice = $('#passengerInsurance'+counter).children().children('.active').find('input:radio').attr("value");
				var mealChoice = $('#passengerMeal'+counter).siblings('input').val();
				mealArray[counter] = mealChoice;
				baggageArray[counter] = baggageChoice;
				insuranceArray[counter] = insuranceChoice;
				/*console.log("Passenger Meal"+counter+": "+mealChoice);
				console.log("Passenger Baggage"+counter+": "+baggageChoice);
				console.log("Passenger Insurance"+counter+": "+insuranceChoice);*/
				counter++;
			}
			for (var i = 0; i < mealArray.length; i++) {
				console.log("Person "+i+": "+mealArray[i]+" "+baggageArray[i]+" "+insuranceArray[i]);
			}
			passAddonsInputs(mealArray,baggageArray,insuranceArray);
			
	});
	
	/*$('.btn-info').on('click',function(){
		$(this).siblings().children().prop('checked',false);
		$('.active').prop('checked',true);
		console.log("sosad");
	});*/
	
	function changeAttribute(idName,index,mealURI,mealName,foodIndex){
		if (mealName == 'None' ){
			$('#passengerMealName'+index).html('<label class="reminder">*Click the image to add.</label>');
		} else {
			$('#passengerMealName'+index).text(mealName);
		}
		
		$('#passengerMeal'+index).attr({'src':'./'+mealURI, 'alt':mealName});
		$('#passengerMeal'+index).siblings('input').attr('value',foodIndex);
	}
	function changeAttributeAll(idName,mealURI,mealName,foodIndex){
		var counter =0;
		while($('#passengerMeal'+counter).length != 0){
			changeAttribute(idName,counter,mealURI,mealName,foodIndex)
			counter = counter +1;
		}
	}
	function passAddonsInputs(mealArray,baggageArray,insuranceArray) {
		console.log("dito ako");
		message = "done";
		$.ajax({
			url : 'add-ons',
			type : 'POST',
			data : {
			"mealArray" : mealArray,
			"baggageArray" : baggageArray,
			"insuranceArray" : insuranceArray,
			"message" : message
		},
		traditional: true,
		async : false,
		cache : false,
		success : function(data) {
			
		},
		error : function(data) {
			alert("Error");
		}
		});
	}
});
