let vehicles = {} || vehicles;
vehicles.iniTable = function(){
    $.ajax({
        url: "api/vehicles",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblVehicle').DataTable({
                destroy:true,
                data:data,
                columns:[
                    {'data':'customer_name'},
                    {'data':'brand_name'},
                    {'data':'color_name'},
                    {'data':'engine_num'},
                    {'data':'chassis_num'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='vehicles.get("+data+")' ><i class=\"fas fa-pen-square fa-3x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='vehicles.delete("+data+")' ><i class=\"fas fa-trash-alt fa-3x\" ></i></a>"
                        }
                    },
                ]
            });
        }
    });
};

vehicles.initBrand = function(){
    $.ajax({
        url : "api/brands",
        method : "GET",
        dataType : "json",
        success: function (data) {
            $('#brand').empty();
            $.each(data,function (i,v) {
                $('#brand').append(
                    "<option value='"+ v.id +"'>"+ v.name +"</option>"
                );
            })
        }
    })
};

vehicles.initColor = function(){
    $.ajax({
        url : "api/colors",
        method : "GET",
        dataType : "json",
        success: function (data) {
            $('#color').empty();
            $.each(data,function (i,v) {
                $('#color').append(
                    "<option value='"+ v.id +"'>"+ v.name +"</option>"
                );
            })
        }
    })
};

vehicles.get = function(){};
vehicles.delete = function(){};
vehicles.save = function(){};

vehicles.init = function(){
    vehicles.iniTable();
    vehicles.initBrand();
    vehicles.initColor();
};
$(document).ready(function () {
    vehicles.init();
});