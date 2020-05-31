let barCharts = {} || barCharts;

Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

barCharts.initData = function () {
    $.ajax({
        url : "api/statistics/brand",
        method : "GET",
        dataType : "json",
        success: function (data) {
            var ctx = document.getElementById("barChart");
            let chart_bar = new Chart(ctx,{
                type: 'bar',
                data:{
                    labels: [data[0].element, data[1].element, data[2].element,  data[3].element, data[4].element, data[5].element],
                    datasets: [{
                        label:"Số lượng",
                        data: [data[0].amount, data[1].amount, data[2].amount, data[3].amount, data[4].amount, data[5].amount],
                        backgroundColor:['#007bff', '#dc3545', '#9a8c98', '#28a745', '#6a4c93','#ffc107'],
                    }]
                },
                options: {
                    scales: {
                        xAxes: [{
                            time: {
                                unit: 'month'
                            },
                            gridLines: {
                                display: false
                            },
                            ticks: {
                                maxTicksLimit: 6
                            }
                        }],
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            },
                            gridLines: {
                                display: true
                            }
                        }],
                    },
                    legend: {
                        display: false
                    }
                }
            });
        }
    })
};



$(document).ready(function () {
    barCharts.initData();
})