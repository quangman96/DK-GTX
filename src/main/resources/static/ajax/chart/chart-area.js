let areaCharts = {} || areaCharts;

areaCharts.initData = function () {
    $.ajax({
        url : "api/statistics/month",
        method : "GET",
        dataType : "json",
        success: function (data) {
            var ctx = document.getElementById("areaChart");
            Chart.defaults.global.defaultFontFamily = 'Lato';
            Chart.defaults.global.defaultFontSize = 18;
            Chart.defaults.global.defaultFontColor = '#777';
            let massPopChart = new Chart(ctx,{
                type: 'line',
                data:{
                    labels: ['Jan','Feb','Mar','Apr','May','Jun'],
                    datasets: [{
                        label:'a1',
                        data: [data[0].amount, data[1].amount, data[2].amount, data[3].amount, data[4].amount, data[5].amount],
                        backgroundColor: 'rgba(54,162,235,0.6)',
                        borderWidth:3,
                        borderColor:'#000'
                    }]
                },
                options: {
                    title:{
                        display:true,
                        text:'Thống kê số phương tiện đăng ký trong năm 2020',
                        fontSize:25
                    },
                    legend:{
                        display:true,
                        position:'right',
                        labels:{
                            fontColor:'#000'
                        }
                    },
                    layout:{
                        padding:{
                            left:50,
                            right:50,
                            bottom:50,
                            top:0
                        }
                    },
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        }
    })
};

$(document).ready(function () {
    areaCharts.initData();
});