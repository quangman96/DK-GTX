let pieCharts = {} || pieCharts;

pieCharts.initData = function () {
    $.ajax({
        url : "api/statistics/province",
        method : "GET",
        dataType : "json",
        success: function (data) {
            console.log(data);
            var ctx = document.getElementById("pieChart");
            Chart.defaults.global.defaultFontFamily = 'Lato';
            Chart.defaults.global.defaultFontSize = 18;
            Chart.defaults.global.defaultFontColor = '#777';
            let pieChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: [data[0].province_name, data[1].province_name,
                        "a","b","c","d"
                        // data[2].province_name, data[3].province_name, data[4].province_name, data[5].province_name
                    ],
                    datasets: [{
                        label: 'a1',
                        data: [data[0].amount, data[1].amount,
                            3,2,5,4,
                            // data[2].amount, data[3].amount,data[4].amount, data[5].amount
                        ],
                        backgroundColor: [
                            'rgba(255,99,132,0.6)',
                            'rgba(54,162,235,0.6)',
                            'rgba(255,206,86,0.6)',
                            'rgba(75,192,192,0.6)',
                            'rgba(153,102,255,0.6)',
                            'rgba(255,159,64,0.6)',
                            'rgba(255,99,132,0.6)'
                        ],
                        borderWidth: 3,
                        borderColor: '#000'
                    }],
                },
            });
        }
    })
};


$(document).ready(function () {
    pieCharts.initData();
});