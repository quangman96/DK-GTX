var contact = contact || {};
console.log("Sdaaaaa");
contact.submitForm = function () {
    var dataObj = {};
    dataObj.firstName = $('#firstName').val();
    dataObj.lastName = $('#lastName').val();
    dataObj.subject = $('#subject').val();
    dataObj.message = $('#message').val();
    $.ajax({
        url:'/api/contact',
        method: 'POST',
        data: JSON.stringify(dataObj),
        dataType: 'json',
        contentType: 'application/json',
    });
    $("#done").html("<h1>" +'Thanks for contact.' + "</h1>");
};