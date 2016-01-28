$(document).ready(function() {
	$("div").click(function() {
		alert ("Hello Jquery");
	});
});


function create() {
	   var counter = 0;
		
	   return {
	      increment: function() {
	         counter++;
	      },
			
	      print: function() {
	    	  document.write("Hello JQuery counter " +counter);
	      }
	   }
	}