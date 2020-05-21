let provinces = {} || provinces;
provinces.iniTable = function(){
    $.ajax({
        url: "api/provinces",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblProvince').DataTable({
                destroy:true,
                data:data,
                columns:[
                    {'data':'name'},
                    {'data':'province_code'},
                    {'data':'telephone_code'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='provinces.get("+data+")' ><i class=\"fas fa-pen-square fa-3x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='provinces.delete("+data+")' ><i class=\"fas fa-trash-alt fa-3x\" ></i></a>"
                        }
                    },
                ]
            });
        }
    });
};

provinces.get = function(){};
provinces.delete = function(){};
provinces.save = function(){};

provinces.init = function(){
    provinces.iniTable();
    // vehicles.initCustomer();
    // vehicles.initBrand();
    // vehicles.initColor();
};
$(document).ready(function () {
    provinces.init();
});