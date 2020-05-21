drawTable = function(){
    $.ajax({
        url:'http://localhost:8080/admin/list-product',
        method : 'GET',
        dataType : 'json',
        contentType : 'application/json',
        success : function(data){
            $('#body').empty();
            $.each(data, function(index, value){


                $('#body').append(
                    "<tr>"+
                    "<td>"+ value.name + "</td>" +
                    "<td>" + value.brand.name + "</td>" +
                    "<td>" + value.category.name + "</td>" +
                    "<td>" + value.amount + "</td>" +
                    "<td>" + value.price + "</td>" +
                    "<td><img width='150px' height='150px' src='/images/"+ value.image+ "'></td>" +
                    "<td><a href='/admin/buy-product/"+value.id+"'>Buy</a></td>" +
                    "<td><a href='/admin/edit-product/"+value.id+"'>Edit</a></td>" +
                    "<td><a href='/admin/delete-product/"+value.id+"'>Delete</a></td>" +
                    "</tr>");
            });
        }
    });
};
$(document).ready(function(){
    drawTable();
});