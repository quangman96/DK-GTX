var ctx = document.getElementById("barChart");
Chart.defaults.global.defaultFontFamily = 'Lato';
Chart.defaults.global.defaultFontSize = 18;
Chart.defaults.global.defaultFontColor = '#777';
let chart_bar = new Chart(ctx,{
    type: 'bar',
    data:{
        labels: ['a','b','c','d','e','f'],
        datasets: [{
            label:'quang man',
            data:[30, 20, 15, 50, 70, 40],
            backgroundColor:[
                'rgba(255,99,132,0.6)',
                'rgba(54,162,235,0.6)',
                'rgba(255,206,86,0.6)',
                'rgba(75,192,192,0.6)',
                'rgba(153,102,255,0.6)',
                'rgba(255,159,64,0.6)',
                'rgba(255,99,132,0.6)'
            ],
            borderWidth:3,
            borderColor:'#000'
        }]
    },
    options: {
        title:{
            display:true,
            text:'quang man',
            fontSize:25
        },
        legend:{
            display:false,
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