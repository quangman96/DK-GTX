var vehicles = {} || vehicles;

vehicles.iniTable = function(){
    $.ajax({
        url: "api/vehicles",
        method: "GET",
        dataType: "JSON",
        success: function (data) {
            $('#tblVehicle').DataTable({
                destroy: true,
                data: data,
                columns:[
                    {'data':'name'},
                    {'data':'address'},
                    {'data':'identity'},
                    {'data':'identity_day'},
                    {'data':'engine_num'},
                    {'data':'chassis_num'},
                    {'data':'colorName'},
                    {'data':'provinceName'},
                    {'data':'brandName'},
                    {'data':'typeName'},
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

vehicles.get = function(id){
    $.ajax({
        url : "api/vehicles/" + id,
        method : "GET",
        dataType : "json",
        success: function (data) {
            console.log(this.url);
            $('#formAddEdit')[0].reset();
            $('#modalTitle').html("Edit vehicles");
            $('#name').val(data.name);
            $('#address').val(data.address);
            $('#identity').val(data.identity);
            $('#identity_day').val(data.identity_day);
            $('#engine_num').val(data.engine_num);
            $('#chassis_num').val(data.chassis_num);
            $('#colorName').val(data.colorName);
            $('#provinceName').val(data.provinceName);
            $('#brandName').val(data.brandName);
            $('#typeName').val(data.typeName);
            $('#id').val(data.id);

            $('#modalAddEdit').modal('show');


        }
    });
};

vehicles.save = function(){
  if($("#formAddEdit").valid()){
      if ($('#id').val()== 0){
          let vehicleObj = {};
          vehicleObj.name = $('#name').val();
          vehicleObj.address = $('#address').val();
          vehicleObj.identity = $('#identity').val();
          // vehicleObj.identity_day = $('#identity_day').val();
          vehicleObj.engine_num = $('#engine_num').val();
          vehicleObj.chassis_num = $('#chassis_num').val();

          let provinceObj = {};
          provinceObj.id = $('#province').val();
          provinceObj.name = $("#province option:selected").html();
          vehicleObj.provinceName = provinceObj;

          let brandObj = {};
          brandObj.id = $('#brand').val();
          brandObj.name = $("#brand option:selected").html();
          vehicleObj.brandName = brandObj;

          let typeObj = {};
          typeObj.id = $('#type').val();
          typeObj.name = $("#type option:selected").html();
          vehicleObj.typeName = typeObj;

          let colorObj = {};
          colorObj.id = $('#color').val();
          colorObj.name = $("#color option:selected").html();
          vehicleObj.colorName = colorObj;

          $.ajax({
              url: "api/vehicles",
              method: "POST",
              dataType: "JSON",
              contentType: "application/json",
              data: JSON.stringify(vehicleObj),
              done: function () {
                  console.log("done");
                  $('#modalAddEdit').modal('hide');
                  $("#tblVehicle").DataTable().ajax.reload();
              },
              success: function (data) {
                  console.log("success");
                  $('#modalAddEdit').modal('hide');
                  $("#tblVehicle").DataTable().ajax.reload();
              }
          });
      }
      else{
          let vehicleObj = {};
          vehicleObj.name = $('#name').val();
          vehicleObj.address = $('#address').val();
          vehicleObj.identity = $('#identity').val();
          vehicleObj.identity_day = $('#identity_day').val();
          vehicleObj.engine_num = $('#engine_num').val();
          vehicleObj.chassis_num = $('#chassis_num').val();
          vehicleObj.id = $('#id').val();
          console.log(vehicleObj.id);

          let provinceObj = {};
          provinceObj.id = $('#province').val();
          provinceObj.name = $("#province option:selected").html();
          vehicleObj.province_id = provinceObj.id;

          let brandObj = {};
          brandObj.id = $('#brand').val();
          brandObj.name = $("#brand option:selected").html();
          vehicleObj.brand_id = brandObj.id;

          let typeObj = {};
          typeObj.id = $('#type').val();
          typeObj.name = $("#type option:selected").html();
          vehicleObj.type_id = typeObj.id;

          let colorObj = {};
          colorObj.id = $('#color').val();
          colorObj.name = $("#color option:selected").html();
          vehicleObj.color_id = colorObj.id;


          $.ajax({
              url: "api/vehicles/" +vehicleObj.id,
              method: "PUT",
              dataType: "JSON",
              contentType: "application/json",
              data: JSON.stringify(vehicleObj),
              success: function (data) {
                $("#modalAddEdit").modal('hide');
                  swal("Done!", "Vehicle was Updated!", "success");
                  vehicles.iniTable();
              }
          });
      }
  }
};

vehicles.initProvince = function(){
  $.ajax({
      url : "api/provinces/",
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

vehicles.initBrand = function(){
    $.ajax({
        url : "api/brands/",
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

vehicles.initType = function(){
    $.ajax({
        url : "api/types/",
        method : "GET",
        dataType : "json",
        success: function (data) {
            $('#type').empty();
            $.each(data,function (i,v) {
                $('#type').append(
                    "<option value='"+ v.id +"'>"+ v.name +"</option>"
                );
            })
        }
    })
};

vehicles.initColor = function(){
    $.ajax({
        url : "api/colors/",
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



vehicles.delete = function(id){
    swal({
        title: "Do you want to remove this vehicle?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        allowOutsideClick: false,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "api/vehicles/delete/" +id,
                    method: "PUT",
                    dataType: "JSON",
                    success: function () {
                        swal("Done!", "It was deleted!", "success");
                        vehicles.iniTable();
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

vehicles.init = function(){
    vehicles.iniTable();
    vehicles.initProvince();
    vehicles.initBrand();
    vehicles.initType();
    vehicles.initColor();
};

$(document).ready(function () {
    vehicles.init();
});