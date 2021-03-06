let provinces = {} || provinces;

provinces.iniTable = function () {
    $.ajax({
        url: "api/provinces",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblProvince').DataTable({
                destroy: true,
                data: data,
                columns: [
                    {'data': 'name'},
                    {'data': 'province_code'},
                    {'data': 'telephone_code'},
                    {'data': 'create_date'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='provinces.get(" + data + ")' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='provinces.delete(" + data + ")' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
        }
    });
};

provinces.get = function (id) {
    $.ajax({
        url: "api/provinces/" + id,
        method: "GET",
        dataType: "json",
        success: function (data) {
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Edit province");
            $('#name').val(data.name);
            $('#province_code').val(data.province_code);
            $('#telephone_code').val(data.telephone_code);
            $('#id').val(data.id);

            $('#modalAddEdit').modal('show');
        }
    })
};

provinces.delete = function (id) {
    swal({
        title: "Bạn có chắc muốn xóa dữ liệu tỉnh thành này?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        allowOutsideClick: false,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "api/provinces/delete/" + id,
                    method: "PUT",
                    // dataType: "JSON",
                    success: function () {
                        swal("Thành công!", "Dữ liệu đã xóa!", "success");
                        provinces.iniTable();
                    },
                    error: function () {
                        swal("Xóa thất bại!", "Vui lòng thử lại!", "error");
                    }
                });
            } else {
            }
        });

};

provinces.save = function () {
    if ($('#formAddEdit').valid()) {
        if ($('#id').val() == 0) {
            let provinceObj = {};
            provinceObj.name = $('#name').val();
            provinceObj.province_code = $('#province_code').val();
            provinceObj.telephone_code = $('#telephone_code').val();
            provinceObj.id = $('#id').val();

            $.ajax({
                url: "api/provinces/" + provinceObj.id,
                method: "POST",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(provinceObj),
                success: function (data) {
                    $("#modalAddEdit").modal('hide');
                    swal("Thành công!", "Dữ liệu đã được tạo!", "success");
                    provinces.iniTable();
                }
            });

        } else {
            let provinceObj = {};
            provinceObj.name = $('#name').val();
            provinceObj.province_code = $('#province_code').val();
            provinceObj.telephone_code = $('#telephone_code').val();
            provinceObj.id = $('#id').val();

            $.ajax({
                url: "api/provinces/" + provinceObj.id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(provinceObj),
                success: function (data) {
                    $("#modalAddEdit").modal('hide');
                    swal("Thành công!", "Dữ liệu đã được cập nhật!", "success");
                    provinces.iniTable();
                }
            });
        }
    }
};

provinces.addNew = function () {
    $('#modalTitle').html("Tạo mới tỉnh thành");
    provinces.resetFrom();
    $('#modalAddEdit').modal('show');
};

provinces.resetFrom = function () {
    $('#formAddEdit')[0].reset();
    let validator = $("#formAddEdit").validate();
    validator.resetForm();
};

provinces.init = function () {
    provinces.iniTable();
};
$(document).ready(function () {
    provinces.init();
});