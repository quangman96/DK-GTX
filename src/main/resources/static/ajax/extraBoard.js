let extraBoard = {} || extraBoard;
let elementName;

extraBoard.initBrand = function(){
    $.ajax({
        url: "api/brands",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblBrand').DataTable({
                "scrollY":        "400px",
                "scrollCollapse": true,
                "paging":         false,
                destroy: true,
                data:data,
                columns:[
                    {'data':'name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='extraBoard.get("+data+",this.id)' id='brands' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='extraBoard.delete("+data+",this.id)' id='brands' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
            $("#tblBrand_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='brands' onclick='extraBoard.addNew(this.id)'>Create new Brand</button>")
        }
    });
};

extraBoard.initColor = function(){
    $.ajax({
        url: "api/colors",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblColor').DataTable({
                "scrollY":        "400px",
                "scrollCollapse": true,
                "paging":         false,
                destroy: true,
                data:data,
                columns:[
                    {'data':'name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='extraBoard.get("+data+",this.id)' id='colors' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='extraBoard.delete("+data+",this.id)' id='colors' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },
                ]
            });
            $("#tblColor_wrapper .row:first-child .col-sm-12:first-child").prepend("<button type=\"button\" class=\"btn btn-secondary\" id='colors' onclick='extraBoard.addNew(this.id)'>Create new Color</button>")
        }
    });
};

extraBoard.get = function(id,element){
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

extraBoard.delete = function(id,element){
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
                    success: function () {
                        swal("Done!", "It was deleted!", "success");
                        extraBoard.init();
                    },
                    error: function () {
                        console.log(this.url);
                        swal("Error deleting!", "Please try again", "error");
                    }
                });
            } else {
                swal("Your data is safe!");
            }
        });
};

extraBoard.save = function(){
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
                    extraBoard.init();
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
                    extraBoard.init();
                }


            })
        }
    }
};

extraBoard.addNew = function(element){
    $('#modalTitle').html("Create new "+element);
    extraBoard.resetFrom();
    $('#modalAddEdit').modal('show');
    elementName = element;
    console.log($('#id').val()+"add");

};

extraBoard.resetFrom = function(){
    $('#formAddEdit')[0].reset();
    let validator = $("#formAddEdit").validate();
    validator.resetForm();
};


extraBoard.init = function(){
    extraBoard.initBrand();
    extraBoard.initColor();
};

$(document).ready(function () {
    extraBoard.init();
});