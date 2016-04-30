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
	            fill: false,
	            lineTension: 0.1,
	            backgroundColor: "rgba(75,192,192,0.4)",
	            borderColor: "rgba(75,192,192,1)",
	            borderCapStyle: 'butt',
	            borderDash: [],
	            borderDashOffset: 0.0,
	            borderJoinStyle: 'miter',
	            pointBorderColor: "rgba(75,192,192,1)",
	            pointBackgroundColor: "#fff",
	            pointBorderWidth: 1,
	            pointHoverRadius: 5,
	            pointHoverBackgroundColor: "rgba(75,192,192,1)",
	            pointHoverBorderColor: "rgba(220,220,220,1)",
	            pointHoverBorderWidth: 2,
	            pointRadius: 1,
	            pointHitRadius: 10,
	            data: valueListFiltered,
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
			  toastr.error('Nie ma danych dla tej daty');
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
			  toastr.error('Nie ma danych dla tej daty');
		  } else {
			  lineChart.data.labels = dateListFiltered;
			  lineChart.data.data = valueListFiltered;
			  lineChart.update();
		  }
	  }
	});
	$("#toDate").datepicker("setDate", dateListFiltered[dateListFiltered.length - 1]);
	
	
});

