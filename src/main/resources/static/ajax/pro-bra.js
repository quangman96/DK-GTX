let fourTable = {} || twoTable;

let elementName;

fourTable.iniTableProvince = function(){
    $.ajax({
        url: "api/provinces",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblProvince').DataTable({
                "scrollY":        "175px",
                "scrollCollapse": true,
                "paging":         false,
                destroy: true,
                data:data,
                columns:[
                    {'data':'name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='fourTable.get("+data+",this.id)' id='provinces' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='fourTable.delete("+data+",this.id)' id='provinces' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
            $("#tblProvince_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='provinces' onclick='fourTable.addNew(this.id)'>Create new Province</button>")
        }
    });
};

fourTable.iniTableBrand = function(){
    $.ajax({
        url: "api/brands",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblBrand').DataTable({
                "scrollY":        "175px",
                "scrollCollapse": true,
                "paging":         false,
                destroy: true,
                data:data,
                columns:[
                    {'data':'name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='fourTable.get("+data+",this.id)' id='brands' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='fourTable.delete("+data+",this.id)' id='brands' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
            $("#tblBrand_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='brands' onclick='fourTable.addNew(this.id)'>Create new Brand</button>")

        }
    });
};

fourTable.iniTableType = function(){
    $.ajax({
        url: "api/types",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblType').DataTable({
                "scrollY":        "175px",
                "scrollCollapse": true,
                "paging":         false,
                destroy: true,
                data:data,
                columns:[
                    {'data':'name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='fourTable.get("+data+",this.id)' id='types' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='fourTable.delete("+data+",this.id)' id='types'><i class=\"fas fa-trash-alt fa-2x\"></i></a>"
                        }
                    },
                ]
            });
            $("#tblType_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='types' onclick='fourTable.addNew(this.id)'>Create new Type</button>")

        }
    });
};

fourTable.iniTableColor = function(){
    $.ajax({
        url: "api/colors",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblColor').DataTable({
                "scrollY":        "175px",
                "scrollCollapse": true,
                "paging":         false,
                destroy: true,
                data:data,
                columns:[
                    {'data':'name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='fourTable.get("+data+",this.id)' id='colors'><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='fourTable.delete("+data+",this.id)' id='colors'><i class=\"fas fa-trash-alt fa-2x\"></i></a>"
                        }
                    },
                ]
            });
            $("#tblColor_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='colors' onclick='fourTable.addNew(this.id)'>Create new Color</button>")

        }
    });
};

fourTable.delete = function(id,element){
    console.log(id);
    console.log(element);
    swal({
        title: "Are you sure?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        allowOutsideClick: false,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "api/"+element+"/delete/" +id,
                    method: "PUT",
                    dataType: "JSON",
                    success: function () {
                        swal("Done!", "It was deleted!", "success");
                        fourTable.init();
                    },
                    error: function () {
                        swal("Error deleting!", "Please try again", "error");
                    }
                });
            } else {
                swal("Your data is safe!");
            }
        });
};

fourTable.get = function(id,element){
    $.ajax({
       url:"api/"+element+"/"+id,
        method: "GET",
        dataType: "JSON",
        success: function (data) {
        $('#formAddEdit')[0].reset();
        $('#modalTitle').html("Edit "+element);
        $('#name').val(data.name);
        $('#id').val(data.id);
        $('#modalAddEdit').modal('show');
        elementName = element;
            console.log($('#id').val()+"edit");
        }
    });
};

fourTable.addNew = function(element){
    $('#modalTitle').html("Create new "+element);
    fourTable.resetFrom();
    $('#modalAddEdit').modal('show');
    elementName = element;
    console.log($('#id').val()+"add");

};

fourTable.initValidation = function(){
    $("#modalAddEdit").validate({
    //validate
    });
};


fourTable.resetFrom = function(){
    $('#formAddEdit')[0].reset();
    let validator = $("#formAddEdit").validate();
    validator.resetForm();
};

fourTable.save = function(){
    if($("#formAddEdit").valid()){
        console.log($('#id').val()+"save");
        if($('#id').val()==0){
            let obj = {};
            obj.name = $('#name').val();

            $.ajax({
                url: "/api/"+elementName,
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(obj),
                success: function (data) {
                    $('#modalAddEdit').modal('hide');
                    swal("Done!","New "+elementName+" was created!","success");
                    fourTable.init();
                },
                error: function () {
                    console.log("error");
                }
            })
            //    post method
        }
        else{
            let obj = {};
            obj.name = $('#name').val();
            obj.id = $('#id').val();

            $.ajax({
                url : "/api/"+elementName+"/"+obj.id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(obj),
                success: function (data) {
                    $('#modalAddEdit').modal('hide');
                    swal("Done!", " ", "success");
                    fourTable.init();
                }


            })
        }
    }
};

fourTable.resetFrom = function(){
    $('#formAddEdit')[0].reset();
    $('#name').val('');
    let validator = $("#formAddEdit").validate();
    validator.resetForm();
};


fourTable.init = function(){
    fourTable.iniTableProvince();
    fourTable.iniTableBrand();
    fourTable.iniTableType();
    fourTable.iniTableColor();
    // fourTable.initValidation();
};

$(document).ready(function () {
    fourTable.init();
});