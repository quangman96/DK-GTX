require(["jquery", "../.."], function($) {

	$.validator.setDefaults({
		submitHandler: function() { alert("submitted!"); }
	});

	// validate the comment form when it is submitted
	$("#commentForm").validate();
});