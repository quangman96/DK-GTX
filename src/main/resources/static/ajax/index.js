let index = {} || index;
let formObjCustomer = {};
let formObjVehicle = {};
let customer_status;
let existCustomer_id;


index.checkVehicle = function () {
    if (($('#input_engine_num').valid()) && ($('#input_chassis_num').valid())) {
        let engine = $('#input_engine_num').val();
        let chassis = $('#input_chassis_num').val();

        $.ajax({
            url: "api/vehicles/check/" + engine + "/" + chassis,
            method: "GET",
            dataType: "json",
            success: function () {
                swal("Mã số đã tồn tại trong hệ thống! Xin thử lại", "", "warning");
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
    if ($('#input_identity').valid()) {
        let identity = $('#input_identity').val();

        $.ajax({
            url: "api/customers/check/" + identity,
            method: "GET",
            dataType: "json",
            success: function (data) {
                customer_status = true;
                swal("Dữ liệu đã có trong hệ thống, tự động cập nhật!");
                existCustomer_id = (data.id);
                $('#input_name').val(data.name);
                $('#input_address').val(data.address);
                $('#input_phone').val(data.phone);
                $('#input_identity').val(data.identity);
                $('#province').html("<option value='" + data.province_id + "'>" + data.province_name + "</option>");

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
                swal("Tạo mới người đăng ký với số CMND/HC là:  " + identity);
                $('#input_identity').prop("disabled", true);
                $('#province').prop("disabled", false);
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

index.createForm = function () {
    if ($('#form_create').valid()) {
        if (customer_status) {
            index.createNewVehicleWithExistCustomer();
        } else {
            index.createNewCustomerAndVehicle();
        }
    } else {
    }
};

index.createNewVehicleWithExistCustomer = function () {

    formObjVehicle.customer_id = existCustomer_id;
    formObjVehicle.vehicle_name = $('#input_vehicle').val();
    formObjVehicle.brand_id = $('#brand').val();
    formObjVehicle.color_id = $('#color').val();
    formObjVehicle.engine_num = ($('#input_engine_num').val()).toUpperCase();
    formObjVehicle.chassis_num = ($('#input_chassis_num').val()).toUpperCase();

    $.ajax({
        url: "api/vehicles",
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
                    if (value == "Có") {
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
        url: "api/customers",
        method: "POST",
        dataType: "JSON",
        contentType: "application/json",
        data: JSON.stringify(formObjCustomer),
        success: function (data) {

            $.ajax({
                url: "api/customers/check/" + formObjCustomer.identity,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    formObjVehicle.customer_id = data.id;

                    $.ajax({
                        url: "api/vehicles",
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
                                    if (value == "Có") {
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
        },
        error: function () {
        }
    });
};

index.detail = function () {
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

index.closeModal = function () {
    $('#modalDetails').modal('hide');
    index.resetAll();
};

index.guide = function () {
    $('#guideTitle').html("Hướng dẫn tạo mới giấy đăng ký xe");
    $('#modalGuide').modal('show');

};

index.resetAll = function () {
    $('#form_create')[0].reset();
    $('#input_name').prop("disabled", false);
    $('#input_identity').prop("disabled", false);
    $('#input_vehicle').prop("disabled", true);
    $('#brand').prop("disabled", true);
    $('#color').prop("disabled", true);
    $('#input_engine_num').prop("disabled", true);
    $('#input_chassis_num').prop("disabled", true);
    $('#check_identity').prop("disabled", true);
    $('#create_form').prop("disabled", true);

    formObjCustomer = {};
    formObjVehicle = {};
    customer_status = null;
    existCustomer_id = null;
};

$.validator.addMethod(
    "regexName",
    function(value, element, regexp) {
        var check = false;
        var re = new RegExp(regexp);
        return this.optional(element) || !/^[0-9!`@#\$%\^\&*\)\(+=._-]+$/g.test(value);

    }
);

$.validator.addMethod(
    "regexPhone",
    function(value, element, regexp) {
        let check = false;
        let re = new RegExp(regexp);
        return this.optional(element) ||  /((09|03|07|08|05)+([0-9]{8})\b)/g.test(value);
    }
);

$.validator.addMethod(
    "notNumber",
    function (value, element, regExp) {
        let check = false;
        let re = new RegExp(regExp);
        return this.optional(element) ||  !/[0-9]/.test(value);
    }
);

index.initValidation = function () {
    $('#form_create').validate({
        rules: {
            input_name: {
                required: true,
                regexName: true,
                notNumber: true,
                maxlength: 50,
            },
            input_address: {
                required: true,
                maxlength: 100,
            },
            input_phone: {
                required: true,
                digits: true,
                regexPhone:true
            },
            input_identity: {
                required: true,
                digits: true,
                maxlength: 12,
                minlength: 9
            },
            input_vehicle: {
                required: true,
                maxlength: 50,
                minlength: 5
            },
            input_engine_num: {
                required: true,
                maxlength: 18,
                minlength: 9
            },
            input_chassis_num: {
                required: true,
                maxlength: 18,
                minlength: 9
            }
        },
        messages: {
            input_name: {
                required: "Tên không được để trống!",
                regexName: "Tên không hợp lệ!",
                notNumber: "Tên không được có số",
                maxlength: "Tên không được nhập quá 50 kí tự",
            },
            input_address: {
                required: "Không được để trống trường này!",
                maxlength: "Dữ liệu không hợp lệ",
            },
            input_phone: {
                required: "Không được để trống trường này!",
                digits: "Dữ liệu phải là số",
                regexPhone: "Số điện thoại không hợp lệ!"
            },
            input_identity: {
                required: "Không được để trống trường này!",
                digits: "Dữ liệu phải là số",
                maxlength: "Dữ liệu không hợp lệ",
                minlength: "CMND ít nhất 9 số"
            },
            input_vehicle: {
                required: "Không được để trống trường này!",
                maxlength: "Dữ liệu không hợp lệ",
                minlength: "Dữ liệu không hợp lệ."
            },
            input_engine_num: {
                required: "Không được để trống trường này!",
                maxlength: "Dữ liệu không hợp lệ",
                minlength: "Số máy ít nhất 9 số"
            },
            input_chassis_num: {
                required: "Không được để trống trường này!",
                maxlength: "Dữ liệu không hợp lệ",
                minlength: "Số khung ít nhất 9 số"

            },
        }
    })
};

index.getDay = function () {
    let toDay = new Date();
    let dd = String(toDay.getDate()).padStart(2, '0');
    let mm = String(toDay.getMonth() + 1).padStart(2, '0');
    let yyyy = toDay.getFullYear();

    toDay = mm + '/' + dd + '/' + yyyy;
    return (toDay);
};

$(document).ready(function () {
    index.initValidation();
});