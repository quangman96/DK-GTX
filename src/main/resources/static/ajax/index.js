let index = {} || index;
let formObjCustomer = {};
let formObjVehicle = {};
let customer_status;
let existCustomer_id;


index.checkVehicle = function () {
    if (($('#input_engine_num').valid())&&($('#input_chassis_num').valid())){
        let engine = $('#input_engine_num').val();
        let chassis = $('#input_chassis_num').val();

        $.ajax({
            url: "api/vehicles/check/" + engine +"/" +chassis,
            method: "GET",
            dataType: "json",
            success: function () {
                swal("Mã số đã tồn tại trong hệ thống! Xin thử lại","","warning");
                $('#input_engine_num').val("");
                $('#input_chassis_num').val("");
            },
            error: function () {
                swal("Số khung và số máy hợp lệ!");
                $('#create_form').prop("disabled", false);
            }
        })
    }
};

index.checkInformation = function () {
    if ($('#input_identity').valid()){
        let identity =$('#input_identity').val();

        $.ajax({
            url : "api/customers/check/" + identity,
            method : "GET",
            dataType : "json",
            success: function (data) {
                customer_status = true;
                swal("Dữ liệu đã có trong hệ thống, tự động cập nhật!");
                existCustomer_id = (data.id);
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
                $('#check_identity').prop("disabled", false);
            },
            error: function () {
                customer_status = false;
                swal("Tạo mới người đăng ký với số CMND/HC là:  "+identity);
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
                $('#check_identity').prop("disabled", false);
            }
        })
    }
};

index.createForm = function(){
    if ($('#form_create').valid()){
        if(customer_status){
            index.createNewVehicleWithExistCustomer();
        }
        else {
            index.createNewCustomerAndVehicle();
        }
    } else {
    }
};

index.createNewVehicleWithExistCustomer = function() {

    formObjVehicle.customer_id = existCustomer_id;
    formObjVehicle.vehicle_name = $('#input_vehicle').val();
    formObjVehicle.brand_id = $('#brand').val();
    formObjVehicle.color_id = $('#color').val();
    formObjVehicle.engine_num = ($('#input_engine_num').val()).toUpperCase();
    formObjVehicle.chassis_num = ($('#input_chassis_num').val()).toUpperCase();

    $.ajax({
        url : "api/vehicles",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(formObjVehicle),
        success: function (data) {
            swal({
                title: "Tạo mới thành công.",
                text: "Bạn có muốn xem lại thông tin không?",
                icon: "success",
                buttons: {
                    cancel: "Không",
                    Có: true,
                },
            })
                .then((value) => {
                    if(value == "Có"){
                        index.detail();
                    } else {
                        index.resetAll();
                    }
                });
        },
        error: function () {
            swal("Tạo mới thất bại!", "Vui lòng thử lại!", "error");
        }
    });
};

index.createNewCustomerAndVehicle = function () {
    formObjCustomer.name = $('#input_name').val();
    formObjCustomer.address = $('#input_address').val();
    formObjCustomer.phone = $('#input_phone').val();
    formObjCustomer.identity = $('#input_identity').val();
    formObjCustomer.province_id = $('#province').val();
    formObjVehicle.vehicle_name = $('#input_vehicle').val();
    formObjVehicle.brand_id = $('#brand').val();
    formObjVehicle.color_id = $('#color').val();
    formObjVehicle.engine_num = ($('#input_engine_num').val()).toUpperCase();
    formObjVehicle.chassis_num = ($('#input_chassis_num').val()).toUpperCase();

    $.ajax({
        url : "api/customers",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(formObjCustomer),
        success: function (data) {
            console.log("create customer");

            $.ajax({
                url : "api/customers/check/" + formObjCustomer.identity,
                method : "GET",
                dataType : "json",
                contentType: "application/json",
                success: function (data) {
                    formObjVehicle.customer_id = data.id;
                    console.log(formObjVehicle.customer_id);

                    $.ajax({
                        url : "api/vehicles",
                        method: "POST",
                        dataType: "JSON",
                        contentType: "application/json",
                        data: JSON.stringify(formObjVehicle),
                        success: function (data) {
                            swal({
                                title: "Tạo mới thành công.",
                                text: "Bạn có muốn xem lại thông tin không?",
                                icon: "success",
                                buttons: {
                                    cancel: "Không",
                                    Có: true,
                                },
                            })
                                .then((value) => {
                                    if(value == "Có"){
                                        index.detail();
                                    } else {
                                        index.resetAll();
                                    }
                                });
                        },
                        error: function () {
                            swal("Tạo mới thất bại!", "Vui lòng thử lại!", "error");
                        }
                    });

                },
                error: function () {
                    swal("Tạo mới thất bại!", "Vui lòng thử lại!", "error");
                }
            });
            console.log(formObjVehicle);
        },
        error: function () {
            console.log("error");
        }
    });
};

index.detail = function() {
    $('#modalTitle').html("CHÚC MỪNG BẠN ĐÃ ĐĂNG KÝ THÀNH CÔNG.");
    $('#detail_name').html($('#input_name').val());
    $('#detail_identity').html($('#input_identity').val());
    $('#detail_address').html($('#input_address').val());
    $('#detail_phone').html($('#input_phone').val());
    $('#detail_province').html($('#province  option:selected').text());
    $('#detail_vehicle').html($('#input_vehicle').val());
    $('#detail_brand').html($('#brand option:selected').text());
    $('#detail_color').html($('#color option:selected').text());
    $('#detail_engine').html($('#input_engine_num').val());
    $('#detail_chassis').html($('#input_chassis_num').val());
    $('#detail_date').html(index.getDay());

    $('#modalDetails').modal('show');
};

index.closeModal = function() {
    $('#modalDetails').modal('hide');
    index.resetAll();
};

index.guide = function(){
    $('#guideTittle').html("Hướng dẫn tạo mới giấy đăng ký xe");
    $('#guide').modal('show');

};

index.resetAll = function () {
    $('#form_create')[0].reset();
    $('#input_name').prop("disabled", false);
    $('#input_identity').prop("disabled", false);
    formObjCustomer = {};
    formObjVehicle = {};
    customer_status = null;
    existCustomer_id = null;
};

index.initValidation = function () {
    $('#form_create').validate({
        rules:{
            input_name:{
                required:true

            },
            input_address:{
                required:true
            },
            input_phone:{
                required:true,
                digits:true,
            },
            input_identity:{
                required:true,
                digits:true,
            },
            input_vehicle:{
                required:true
            },
            input_engine_num:{
                required:true,
            },
            input_chassis_num:{
                required:true,
            }
        },
        messages:{
            input_name:{
                required: "Không được để trống trường này!"
            },
            input_address:{
                required: "Không được để trống trường này!"
            },
            input_phone:{
                required: "Không được để trống trường này!",
                digits:"Dữ liệu phải là số"
            },
            input_identity:{
                required: "Không được để trống trường này!",
                digits:"Dữ liệu phải là số"
            },
            input_vehicle:{
                required: "Không được để trống trường này!",
            },
            input_engine_num:{
                required: "Không được để trống trường này!",
            },
            input_chassis_num:{
                required: "Không được để trống trường này!",

            },
        }
    })
};

index.getDay = function() {
    let toDay = new Date();
    let dd = String(toDay.getDate()).padStart(2, '0');
    let mm = String(toDay.getMonth() + 1).padStart(2, '0');
    let yyyy = toDay.getFullYear();

    toDay = mm + '/' + dd + '/' + yyyy;
    return(toDay);
};

$(document).ready(function () {
    index.initValidation();
});