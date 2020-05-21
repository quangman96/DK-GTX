let customers = {} || customers;

customers.iniTable = function(){
    $.ajax({
        url: "customers",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblCustomer').DataTable({
                destroy: true,
                data: data,
                columns:[
                    {'data':'name'},
                    {'data':'address'},
                    {'data':'phone'},
                    {'data':'identity'},
                    {'data':'province_name'},
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='edit' onclick='customers.get("+data+")' ><i class=\"fas fa-pen-square fa-2x\" ></i></a>"
                        }
                    },
                    {
                        data: 'id',
                        render: function (data, type, row, meta) {
                            return "<a href='javascript:void(0);' class='delete' onclick='customers.delete("+data+")' ><i class=\"fas fa-trash-alt fa-2x\" ></i></a>"
                        }
                    },

                ]
            })

        }
    });
};

customers.initProvince = function(){
    $.ajax({
        url : "provinces/",
        method : "GET",
        dataType : "json",
        success: function (data) {
            $('#province').empty();
            $.each(data,function (i,v) {
                $('#province').append(
                    "<option value='"+ v.id +"'>"+ v.name +"</option>"
                );
            })
        }
    })
};

customers.get = function(id){
    $.ajax({
        url : "customers/" + id,
        method : "GET",
        dataType : "json",
        success: function (data) {
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Edit customer");
            $('#name').val(data.name);
            $('#address').val(data.address);
            $('#phone').val(data.phone);
            $('#identity').val(data.identity);
            $('#province_name').val(data.province);
            $('#id').val(data.id);

            $('#modalAddEdit').modal('show');
        }
    })
};

customers.save = function(){
    if($('#formAddEdit').valid()){
        if($('#id').val()==0){
            console.log("error id = 0 ");
            // let customerObj = {};
            // customerObj.name = $('#name').val();
            // customerObj.address = $('#address').val();
            // customerObj.phone = $('#phone').val();
            // customerObj.identity = $('#identity').val();
            //
            // let provinceObj = {};
            // provinceObj.id = $('#province').val();
            // provinceObj.name = $('#province option:selected').html();
            // customerObj.province_name = provinceObj;
        }
        else{
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
            console.log(customerObj);

            $.ajax({
                url: "customers/" +customerObj.id,
                method: "PUT",
                dataType: "JSON",
                contentType: "application/json",
                data: JSON.stringify(customerObj),
                success: function (data) {
                    $("#modalAddEdit").modal('hide');
                    swal("Done!", "Vehicle was Updated!", "success");
                    customers.iniTable();
                }

            });
        }
    }
};

customers.delete = function(id){
    swal({
        title: "Do you want to remove this customer?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        allowOutsideClick: false,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "customers/delete/" +id,
                    method: "PUT",
                    // dataType: "JSON",
                    success: function () {
                        swal("Done!", "It was deleted!", "success");
                        customers.iniTable();
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


customers.init = function(){
    customers.iniTable();
    customers.initProvince();
};

$(document).ready(function () {
    customers.init();
});