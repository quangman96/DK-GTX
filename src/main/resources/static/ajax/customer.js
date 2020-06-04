let customers = {} || customers;
let identityList = [];
let identityExist;

customers.iniTable = function () {
    $.ajax({
        url: "api/customers",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblCustomer').DataTable({
                destroy: true,
                data: data,
                columns: [
                    {'data': 'name'},
                    {'data': 'address'},
                    {'data': 'phone'},
                    {'data': 'identity'},
                    {'data': 'province_name'},
                    {'data': 'create_date'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='customers.get(" + data + ")' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='customers.delete(" + data + ")' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },

                ]
            })

        }
    });
};

customers.initProvince = function () {
    $.ajax({
        url: "api/provinces",
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#province').empty();
            $.each(data, function (i, v) {
                $('#province').append(
                    "<option value='" + v.id + "'>" + v.name + "</option>"
                );
            })
        }
    })
};

customers.hideModal = function() {
    identityExist = null;
    $('#modalAddEdit').modal('hide');
};


customers.get = function (id) {
    $.ajax({
        url: "api/customers/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Chỉnh sửa dữ liệu");
            $('#name').val(data.name);
            $('#address').val(data.address);
            $('#phone').val(data.phone);
            $('#identity').val(data.identity);
            identityExist = (data.identity);
            $('#province').val(data.province_id);
            $('#id').val(data.id);

            $('#modalAddEdit').modal('show');
        }
    })
};

customers.save = function () {
    if ($('#formAddEdit').valid()) {
        if ($('#id').val() == 0) {
        } else {
            let customerObj = {};
            customerObj.name = $('#name').val();
            customerObj.address = $('#address').val();
            customerObj.phone = $('#phone').val();
            customerObj.identity = $('#identity').val();
            customerObj.id = $('#id').val();

            let provinceObj = {};
            provinceObj.id = $('#province').val();
            provinceObj.name = $('#province option:selected').html();
            customerObj.province_id = provinceObj.id;


            $.ajax({
                url: "api/customers/" + customerObj.id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(customerObj),
                success: function (data) {
                    $("#modalAddEdit").modal('hide');
                    swal("Thành công!", "Dữ liệu đã được cập nhật!", "success");
                    identityExist = null;
                    customers.iniTable();

                }

            });
        }
    }
};

customers.delete = function (id) {
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
                    url: "api/customers/delete/" + id,
                    method: "PUT",
                    // dataType: "JSON",
                    success: function () {
                        swal("Thành công!", "Dữ liệu đã xóa!", "success");
                        customers.iniTable();
                    },
                    error: function () {
                        swal("Xóa thất bại!", "Vui lòng thử lại!", "error");
                    }
                });
            } else {
            }
        });

};

$.validator.addMethod('checkIdentity', function (value, element) {
    return this.optional(element) || customers.checkIdentity(value)
}, 'Số CMND/HC đã tồn tại trong hệ thống.');

// $.validator.addMethod('regex',function (value, element, regExp) {
//     let check = false;
//     let re = new RegExp(regExp);
//     return this.optional(element) || re.test(value);
// }, 'false');

customers.initValidation = function () {
    $("#formAddEdit").validate({
        rules: {
            name: "required",
            address: "required",
            phone: "required",
            identity: {
                required: true,
                checkIdentity: true,
                // regex: /[^A-Za-z\d\-\=\~\!@#\%&\*\(\)_\+\\\/<>\?\{\}\.\$‘\^\+\"\';:,\s]/
            },
            chassis_num: {
                required: true,
                checkChassisNumber: true,
            },
            engine_num: {
                required: true,
                checkEngineNumber: true,
            },

        },
        messages: {
            name: "Không được để trống trường này!",
            address: "Không được để trống trường này!",
            phone: "Không được để trống trường này!",
            identity: {
                required: "Không được để trống trường này!",
            },
            chassis_num: {
                required: "Không được để trống trường này!",
            },
            engine_num: {
                required: "Không được để trống trường này!",
            }
        }
    });
};

customers.identityList = function () {
    $.ajax({
        url: "api/identity",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            identityList = data;
        },
        error: function () {
        }
    });
};

customers.checkIdentity = function (identity) {
    if (identity == identityExist) {
        return true;
    } else {
        for (let i = 0; i < identityList.length; i++) {
            if (identity == identityList[i]) {
                return false;
            }
        }
        return true;
    }
};


customers.init = function () {
    customers.iniTable();
    customers.initProvince();
    customers.initValidation();
    customers.identityList();
};

$(document).ready(function () {
    customers.init();
});