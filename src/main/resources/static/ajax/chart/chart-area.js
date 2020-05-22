var ctx = document.getElementById("areaChart");
Chart.defaults.global.defaultFontFamily = 'Lato';
Chart.defaults.global.defaultFontSize = 18;
Chart.defaults.global.defaultFontColor = '#777';
let massPopChart = new Chart(ctx,{
    type: 'line',
    data:{
        labels: ['a','b','c','d','e','f','g','h'],
        datasets: [{
            label:'a1',
            data:[
                30,
                20,
                15,
                40,
                50,
                70,
                60,
                40
            ],
            backgroundColor: 'rgba(54,162,235,0.6)',
            borderWidth:3,
            borderColor:'#000'
        }]
    },
    options: {
        title:{
            display:true,
            text:'CHART JS EXAMPLE',
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