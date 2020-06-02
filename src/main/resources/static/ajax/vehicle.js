let vehicles = {} || vehicles;
let engineNumberList = [];
let chassisNumberList = [];
let engineNumberExist;
let chassisNumberExist;

vehicles.iniTable = function () {
    $.ajax({
        url: "api/vehicles",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblVehicle').DataTable({
                destroy: true,
                data: data,
                columns: [
                    {'data': 'vehicle_name'},
                    {'data': 'customer_identity'},
                    {'data': 'brand_name'},
                    {'data': 'color_name'},
                    {'data': 'engine_num'},
                    {'data': 'chassis_num'},
                    {'data': 'create_date'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='vehicles.get(" + data + ")' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='vehicles.delete(" + data + ")' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
        }
    });
};

vehicles.initBrand = function () {
    $.ajax({
        url: "api/brands",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#brand').empty();
            $.each(data, function (i, v) {
                $('#brand').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            })
        }
    })
};

vehicles.initColor = function () {
    $.ajax({
        url: "api/colors",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#color').empty();
            $.each(data, function (i, v) {
                $('#color').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            })
        }
    })
};

vehicles.get = function (id) {
    $.ajax({
        url: "api/vehicles/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Edit vehicle");
            $('#vehicle_name').val(data.vehicle_name);
            $('#customer_name').val(data.customer_name);
            $('#brand').val(data.brand_id);
            $('#color').val(data.color_id);
            $('#engine_num').val(data.engine_num);
            $('#chassis_num').val(data.chassis_num);
            $('#id').val(data.id);
            $('#customer_id').val(data.customer_id);
            engineNumberExist = ($('#engine_num').val()).toUpperCase();
            chassisNumberExist = ($('#chassis_num').val()).toUpperCase();

            $('#modalAddEdit').modal('show');
        }
    })
};

vehicles.delete = function (id) {
    swal({
        title: "Bạn có chắc muốn xóa dữ liệu này?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        allowOutsideClick: false,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "api/vehicles/delete/" + id,
                    method: "PUT",
                    // dataType: "JSON",
                    success: function () {
                        swal("Thành công!", "Dữ liệu đã xóa!", "success");
                        vehicles.iniTable();
                    },
                    error: function () {
                        swal("Xóa thất bại!", "Vui lòng thử lại!", "error");
                    }
                });
            } else {

            }
        });

};

vehicles.save = function () {
    if ($('#formAddEdit').valid()) {
        if ($('#id').val() == 0) {
        } else {
            let vehicleObj = {};
            vehicleObj.vehicle_name = $('#vehicle_name').val();
            vehicleObj.engine_num = ($('#engine_num').val()).toUpperCase();
            vehicleObj.chassis_num = ($('#chassis_num').val()).toUpperCase();
            vehicleObj.id = $('#id').val();
            vehicleObj.customer_id = $('#customer_id').val();

            let brandObj = {};
            brandObj.id = $('#brand').val();
            brandObj.name = $('#brand option:selected').html();
            vehicleObj.brand_id = brandObj.id;

            let colorObj = {};
            colorObj.id = $('#color').val();
            colorObj.name = $('#color option:selected').html();
            vehicleObj.color_id = colorObj.id;

            $.ajax({
                url: "api/vehicles/" + vehicleObj.id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(vehicleObj),
                success: function (data) {
                    console.log(vehicleObj);
                    $("#modalAddEdit").modal('hide');
                    swal("Thành công!", "Dữ liệu đã được cập nhật!", "success");
                    engineNumberExist = null;
                    chassisNumberExist = null;
                    vehicles.iniTable();
                }

            });

        }
    }
};

vehicles.checkEngineNumber = function (engine_num) {
    if ((engine_num) == engineNumberExist) {
        return true;
    } else {
        for (let i = 0; i < engineNumberList.length; i++) {
            if ((engine_num) == engineNumberList[i]) {
                return false;
            }
        }
        return true;
    }
};

vehicles.checkChassisNumber = function (chassis_num) {
    if ((chassis_num.toUpperCase()) == chassisNumberExist) {
        return true;
    } else {
        for (let i = 0; i < chassisNumberList.length; i++) {
            if ((chassis_num.toUpperCase()) == chassisNumberList[i]) {
                return false;
            }
        }
        return true;
    }
};

$.validator.addMethod('checkEngineNumber', function (value, element) {
    return this.optional(element) || vehicles.checkEngineNumber(value)
}, 'Lỗi, số máy đã tồn tại trong hệ thống.');

$.validator.addMethod('checkChassisNumber', function (value, element) {
    return this.optional(element) || vehicles.checkChassisNumber(value)
}, 'Lỗi, số khung đã tồn tại trong hệ thống.');

$("#formAddEdit").submit(function (event) {
    event.preventDefault();
    if (!$(this).valid()) return false;
    console.log("error");
});

vehicles.initValidation = function () {
    $("#formAddEdit").validate({
        rules: {
            vehicle_name: "required",
            chassis_num: {
                required: true,
                checkEngineNumber: true,
            }
        },
        messages: {
            vehicle_name: "Please enter your city vehicle name",
            chassis_num: {
                required: "Please enter your chassis_num",
            }
        }
    });
};

vehicles.initEngineNumberList = function () {
    $.ajax({
        url: "api/engine_num",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            engineNumberList = data;
        },
        error: function () {
        }
    });
};

vehicles.initChassisNumberList = function () {
    $.ajax({
        url: "api/chassis_num",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            chassisNumberList = data;
        },
        error: function () {
        }
    });
};

vehicles.init = function () {
    vehicles.iniTable();
    vehicles.initBrand();
    vehicles.initColor();
    customers.initValidation();
    vehicles.initEngineNumberList();
    vehicles.initChassisNumberList();
};
$(document).ready(function () {
    vehicles.init();
});