// let myChart = document.getElementById('myChart').getContext('2d');
var ctx = document.getElementById("pieChart");
Chart.defaults.global.defaultFontFamily = 'Lato';
Chart.defaults.global.defaultFontSize = 18;
Chart.defaults.global.defaultFontColor = '#777';
let pieChart = new Chart(ctx,{
    type: 'pie',
    data:{
        labels: ['a','b','c','d','e','f'],
        datasets: [{
            label:'a1',
            data:[30, 20, 15, 40, 50, 70],
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
        }],
    },
});