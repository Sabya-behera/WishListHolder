$.ajax({
	/* for pie chart */
	url: "pieChart",
	success: function(result){
		/* pie chart starts here */
		var series = [];
		var data = [];

		for(var i = 0; i < result.length; i++){
			var object = {};
			object.favourites = result[i].favourites.toUpperCase();
			object.y = result[i].yaxis;
			data.push(object);
		}
		var seriesObject = {
			name: 'Favourites',
			colorByPoint: true,
			data: data
		};
		series.push(seriesObject);
		drawPieChart(series);

		/* pie chart ends here */
	}
});
function drawPieChart(series){
	Highcharts.chart('container', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Browser market shares in January, 2018'
	    },
	    tooltip: {
	    	formatter: function() {
	    		return '<strong>'+this.key+': </strong>'+ this.y;
		    }
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.y}'
	            }
	        }
	    },
	    series: series
	});
}