let extraBoard = {} || extraBoard;
let elementName;

extraBoard.initBrand = function () {
    $.ajax({
        url: "api/brands",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblBrand').DataTable({
                "scrollY": "400px",
                "scrollCollapse": true,
                "paging": false,
                destroy: true,
                data: data,
                columns: [
                    {'data': 'name'},
                    {'data': 'create_date'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='extraBoard.get(" + data + ",this.id)' id='brands' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='extraBoard.delete(" + data + ",this.id)' id='brands' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
            $("#tblBrand_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='brands' onclick='extraBoard.addNew(this.id)'>Tạo mới Hãng xe</button>")
        }
    });
};

extraBoard.initColor = function () {
    $.ajax({
        url: "api/colors",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblColor').DataTable({
                "scrollY": "400px",
                "scrollCollapse": true,
                "paging": false,
                destroy: true,
                data: data,
                columns: [
                    {'data': 'name'},
                    {'data': 'create_date'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='extraBoard.get(" + data + ",this.id)' id='colors' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='extraBoard.delete(" + data + ",this.id)' id='colors' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
            $("#tblColor_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='colors' onclick='extraBoard.addNew(this.id)'>Tạo mới màu xe</button>")
        }
    });
};

extraBoard.get = function (id, element) {
    $.ajax({
        url: "api/" + element + "/" + id,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Edit " + element);
            $('#name').val(data.name);
            $('#id').val(data.id);
            $('#modalAddEdit').modal('show');
            elementName = element;
        }
    });
};

extraBoard.delete = function (id, element) {
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
                    url: "api/" + element + "/delete/" + id,
                    method: "PUT",
                    success: function () {
                        swal("Thành công!", "Dữ liệu đã xóa!", "success");
                        extraBoard.init();
                    },
                    error: function () {
                        swal("Xóa thất bại!", "Vui lòng thử lại!", "error");
                    }
                });
            } else {
            }
        });
};

extraBoard.save = function () {
    if ($("#formAddEdit").valid()) {
        if ($('#id').val() == 0) {
            let obj = {};
            obj.name = $('#name').val();

            $.ajax({
                url: "/api/" + elementName,
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(obj),
                success: function (data) {
                    $('#modalAddEdit').modal('hide');
                    swal("Thành công!", "Dữ liệu đã được tạo!", "success");
                    extraBoard.init();
                },
                error: function () {
                    swal("Tạo mới thất bại!", "Vui lòng thử lại!", "error");
                }
            })
            //    post method
        } else {
            let obj = {};
            obj.name = $('#name').val();
            obj.id = $('#id').val();

            $.ajax({
                url: "/api/" + elementName + "/" + obj.id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(obj),
                success: function (data) {
                    $('#modalAddEdit').modal('hide');
                    swal("Thành công!", "Dữ liệu đã được cập nhật!", "success");
                    extraBoard.init();
                },
                error: function () {
                    swal("Chỉnh sửa thất bại!", "Vui lòng thử lại!", "error");
                }


            })
        }
    }
};

extraBoard.addNew = function (element) {
    $('#modalTitle').html("Tạo mới dữ liệu");
    extraBoard.resetFrom();
    $('#modalAddEdit').modal('show');
    elementName = element;
};

extraBoard.resetFrom = function () {
    $('#formAddEdit')[0].reset();
    let validator = $("#formAddEdit").validate();
    validator.resetForm();
};


extraBoard.init = function () {
    extraBoard.initBrand();
    extraBoard.initColor();
};

$(document).ready(function () {
    extraBoard.init();
});