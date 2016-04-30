$().ready(function() {
    $('#table').datatable({
        pageSize: 15,
        sort: [true, true, true],
        filters: [false, true, false],
        filterText: 'Filtruj date'
    }) ;
	
	var valueListFiltered = valueList.reverse();
	var dateListFiltered = dateList.reverse();
	var from = 0;
	var to = dateList.length - 1;
	
	var data = {
		    labels: dateListFiltered,
		    datasets: [{
	            label: "Wartość jednostki",
	            // Boolean - if true fill the area under the line
	            fill: false,
	            lineTension: 0.1,
	            // String - the color to fill the area under the line with if fill is true
	            backgroundColor: "rgba(75,192,192,0.4)",

	            // String - Line color
	            borderColor: "rgba(75,192,192,1)",

	            // String - cap style of the line. See https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/lineCap
	            borderCapStyle: 'butt',

	            // Array - Length and spacing of dashes. See https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/setLineDash
	            borderDash: [],

	            // Number - Offset for line dashes. See https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/lineDashOffset
	            borderDashOffset: 0.0,

	            // String - line join style. See https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/lineJoin
	            borderJoinStyle: 'miter',

	            // The properties below allow an array to be specified to change the value of the item at the given index

	            // String or Array - Point stroke color
	            pointBorderColor: "rgba(75,192,192,1)",

	            // String or Array - Point fill color
	            pointBackgroundColor: "#fff",

	            // Number or Array - Stroke width of point border
	            pointBorderWidth: 1,

	            // Number or Array - Radius of point when hovered
	            pointHoverRadius: 5,

	            // String or Array - point background color when hovered
	            pointHoverBackgroundColor: "rgba(75,192,192,1)",

	            // String or Array - Point border color when hovered
	            pointHoverBorderColor: "rgba(220,220,220,1)",

	            // Number or Array - border width of point when hovered
	            pointHoverBorderWidth: 2,

	            // Number or Array - the pixel size of the point shape. Can be set to 0 to not render a circle over the point
	            // Used to be called "radius" but was renamed for consistency. The old option name continues to work for compatibility.
	            pointRadius: 1,

	            // Number or Array - the pixel size of the non-displayed point that reacts to mouse hover events
	            //
	            // Used to be called "hitRadius" but was renamed for consistency. The old option name continues to work for compatibility.
	            pointHitRadius: 10,

	            // The actual data
	            data: valueListFiltered,

	            // String - If specified, binds the dataset to a certain y-axis. If not specified, the first y-axis is used. First id is y-axis-0
	            yAxisID: "y-axis-0",
	        }]
		};

	var ctx = document.getElementById("lineChart").getContext("2d");
	var lineChart = new Chart(ctx, {
		    type: 'line',
		    data: data,
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});

	
	$( "#fromDate" ).datepicker({
	  dateFormat: "dd-mm-yy",
	  onSelect: function(dateText) {
		  from = _.indexOf(dateList, dateText);
		  dateListFiltered = dateList.slice(from, to + 1);
		  valueListFiltered = valueList.slice(from, to + 1);

		  if (from == -1) {
			  $("#error").text("nie ma danych dla tej daty");
		  } else {
			  lineChart.data.labels = dateListFiltered;
			  lineChart.data.data = valueListFiltered;
			  lineChart.update();
		  }
	  }
	});
	$("#fromDate").datepicker("setDate", dateListFiltered[0]);
	
	$( "#toDate" ).datepicker({
	  dateFormat: "dd-mm-yy",
	  onSelect: function(dateText) {
		  to = _.indexOf(dateList, dateText);
		  dateListFiltered = dateList.slice(from, to + 1);
		  valueListFiltered = valueList.slice(from, to + 1);

		  if (to == -1) {
			  $("#error").text("nie ma danych dla tej daty");
		  } else {
			  lineChart.data.labels = dateListFiltered;
			  lineChart.data.data = valueListFiltered;
			  lineChart.update();
		  }
	  }
	});
	$("#toDate").datepicker("setDate", dateListFiltered[dateListFiltered.length - 1]);
	
	
});

