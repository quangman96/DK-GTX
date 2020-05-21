var checkout = checkout || {};
checkout.submitForm = function () {
    var dataObj = {};
    dataObj.name = $('#name').val();
    dataObj.country = $('#country').val();
    dataObj.address = $('#address').val();
    dataObj.phone = $('#phone').val();
    dataObj.email = $('#email').val();

    $.ajax({
        url:'/checkout',
        method: 'POST',
        data: JSON.stringify(dataObj),
        dataType: 'json',
        contentType: 'application/json',
    });
    $("#done").html("<h1>" +'Thanks .' + "</h1>");
};