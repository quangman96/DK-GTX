let pieCharts = {} || pieCharts;

pieCharts.initData = function () {
    $.ajax({
        url : "api/statistics/province",
        method : "GET",
        dataType : "json",
        success: function (data) {
            console.log(data);
            var ctx = document.getElementById("pieChart");
            Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
            Chart.defaults.global.defaultFontColor = '#292b2c';
            let pieChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: [data[0].element, data[1].element, data[2].element,  data[3].element, data[4].element, data[5].element],
                    datasets: [{
                        data: [data[0].amount, data[1].amount, data[2].amount, data[3].amount, data[4].amount, data[5].amount],
                        backgroundColor: ['#007bff', '#dc3545', '#9a8c98', '#28a745', '#6a4c93','#ffc107'],
                        // borderWidth: 3,
                        // borderColor: '#000'
                    }],
                },
            });
        }
    })
};


$(document).ready(function () {
    pieCharts.initData();
});