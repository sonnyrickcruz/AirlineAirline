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
		}
		});
	$("#submitAddons").on('click',function(){
			var addOns =[];
			var counter=0;
			while($('#passengerBaggage'+counter).length != 0){
				var baggageChoice = $('#passengerBaggage'+counter).children().children('.active').find('input:radio').attr("value");
				var insuranceChoice = $('#passengerInsurance'+counter).children().children('.active').find('input:radio').attr("value");
				var mealChoice = $('#passengerMeal'+counter).siblings('input').val();
				addOns.push({
					meal: mealChoice,
					baggage: baggageChoice,
					insurance: insuranceChoice
				})
				counter++;
			}
			
			passAddonsInputs(addOns);
			
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
	function passAddonsInputs(addOns) {
		console.log(addOns);
		
		message = "done";
		$.ajax({
			url : 'add-ons',
			type : 'POST',
			data : {
			"addOns" : addOns,
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
	
	//Scroll to fixed
	 $('#bookingSteps').scrollToFixed({
         marginTop: $('#header').outerHeight(true) + 10,
	 });
	 $('#header').scrollToFixed();


     // Dock the footer to the bottom of the page, but scroll up to reveal more
     // content if the page is scrolled far enough.

     /*$('.footer').scrollToFixed( {
         bottom: 0,
         limit: $('.footer').offset().top
     });*/
	 var summaries = $('#sidebar');
	 
     summaries.each(function(i) {
         var summary = $(summaries[i]);
         var next = summaries[i + 1];

         summary.scrollToFixed({
             marginTop: $('#header').outerHeight(true) + 10,
             /*limit: function() {
                 var limit = 0;
                 if (next) {
                     limit = $(next).offset().top - $(this).outerHeight(true) - 10;
                 } else {
                     limit = $('.footer').offset().top - $(this).outerHeight(true) - 10;
                 }
                 return limit;
             },*/
             zIndex: 999
         });
     });
	
});
