let index = {} || index;
let formObj = {};

index.checkInformation = function () {
    let identity =$('#input_identity').val();

    if(identity == ""){
        console.log("xu ly null");
        swal("Please input identity!","", "error");
    } else {
        $.ajax({
            url : "api/customers/identity/" + identity,
            method : "GET",
            dataType : "json",
            success: function (data) {
                console.log(data);
                swal("Found customer in system, auto fill!");

                formObj.id = data.id;
                formObj.name = data.name;
                formObj.address = data.address;
                formObj.phone = data.phone;
                formObj.identity = data.identity;


                $('#input_name').val(data.name);
                $('#input_address').val(data.address);
                $('#input_phone').val(data.phone);
                $('#input_identity').val(data.identity);
                $('#province').html("<option value='"+ data.province_id +"'>"+ data.province_name +"</option>");
                $('#input_name').prop("disabled", true);
                $('#input_identity').prop("disabled", true);

                $('#engine_num').prop("disabled", false);
                $('#chassis_num').prop("disabled", false);
                $('#brand').prop("disabled", false);
                $('#color').prop("disabled", false);
                $('#input_vehicle').prop("disabled", false);
                $('#input_chassis_num').prop("disabled", false);
                $('#input_engine_num').prop("disabled", false);
            },
            error: function () {
                console.log("khong co cmnd nay`");
                swal("create new customer with identity: "+identity);
                $('#input_identity').prop("disabled", true);
                $('#province').prop("disabled",false);
                $('#input_name').prop("disabled", false);
                $('#input_address').prop("disabled", false);
                $('#input_phone').prop("disabled", false);

                $('#engine_num').prop("disabled", false);
                $('#chassis_num').prop("disabled", false);
                $('#brand').prop("disabled", false);
                $('#color').prop("disabled", false);
                $('#input_vehicle').prop("disabled", false);
                $('#input_chassis_num').prop("disabled", false);
                $('#input_engine_num').prop("disabled", false);

            }
        })
    }
};

index.checkVehicle = function () {

};