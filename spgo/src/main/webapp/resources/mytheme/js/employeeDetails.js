jQuery(document).ready(function() {
	var email = $('#email').text();	
    $(".btnEdit" ).click(function() {
    	 $.ajax({
             url : '/employee/edit',
             type: "GET",
             data: { id : email },
             success : function(data) {
                
             },
             error : function(data){
            	
             }
         });    	
	   });
});
