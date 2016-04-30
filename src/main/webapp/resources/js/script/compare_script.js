$().ready(function() {
	
	var valueListFiltered = valueList.reverse();
	var dateListFiltered = dateList.reverse();
	var from = 0;
	var to = dateList.length - 1;
	var unitAmount; 
	
	var data = {
		    labels: dateListFiltered,
		    datasets: [{
	            label: "Stan portfela dla funduszu",
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
	        },
	        {
	            label: "Stan portfela dla lokaty",
	            fill: false,
	            lineTension: 0.1,
	            backgroundColor: "rgba(255,192,192,0.4)",
	            borderColor: "rgba(255,192,192,1)",
	            borderCapStyle: 'butt',
	            borderDash: [],
	            borderDashOffset: 0.0,
	            borderJoinStyle: 'miter',
	            pointBorderColor: "rgba(255,192,192,1)",
	            pointBackgroundColor: "#fff",
	            pointBorderWidth: 1,
	            pointHoverRadius: 5,
	            pointHoverBackgroundColor: "rgba(255,192,192,1)",
	            pointHoverBorderColor: "rgba(255,220,220,1)",
	            pointHoverBorderWidth: 2,
	            pointRadius: 1,
	            pointHitRadius: 10,
	            data: valueListFiltered,
	            yAxisID: "y-axis-0",
	        }
	        ]
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
		  
		  compare();
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
		  
		  compare();
	  }
	});
	$("#toDate").datepicker("setDate", dateListFiltered[dateListFiltered.length - 1]);
	
	var compare = function() {
		var investment = parseInt($("#investment").val());
		var unitVal = valueListFiltered[0];
		unitAmount = investment / unitVal;
		$("#units").text("Dysponujesz " + unitAmount + " jednostkami");
		lineChart.data.datasets[0].data = _.map(valueListFiltered, function(el) {
			return el * unitAmount;
		});
		
		var percent = (parseFloat($("#percent").val()) / 100) / 365;
		var tmp = investment;
		
		lineChart.data.datasets[1].data = _.map(valueListFiltered, function(el) {
			tmp = tmp +  investment * percent;
			return  tmp;
		});
		
		lineChart.update();
	};
	compare();
	
	$("#investment").blur(function() {
		compare();
	});
	
	$("#percent").blur(function() {
		compare();
	});
});

