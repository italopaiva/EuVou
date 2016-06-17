$(document).ready{

	$("#go_back_btn").click(function(event){
		event.preventDefault();
		history.goback("-1");
	});
}