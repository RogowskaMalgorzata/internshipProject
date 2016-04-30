<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
 

<script>
	var fundList = [];
	var dateList = [];
	var valueList = [];
	<c:forEach var="fund" items="${fundList}">
		dateList.push("${fund.getParsedDate()}");
		valueList.push("${fund.getValue()}");
	</c:forEach>
</script> 

<script src="<c:url value="/resources/js/underscore-min.js" />"></script> 
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.11.4.custom/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/js/Chart.js/dist/Chart.min.js" />"></script>
<script src="<c:url value="/resources/js/datatable.jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/datatable.min.js" />"></script>
<script src="<c:url value="/resources/js/toastr.min.js" />"></script>
<script src="<c:url value="/resources/js/script/home_script.js" />"></script>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/themes/dark-hive/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/themes/dark-hive/theme.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/datatable-bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/toastr.min.css" />" rel="stylesheet">
</head>
<body>
	<div class="navbar">
		<ul>
		  <li><a href="http://tabiapp.azurewebsites.net/">Home</a></li>
		  <li><a href="http://tabiapp.azurewebsites.net/compare">Compare</a></li>
		</ul> 
	</div>
	<h1>Kurs giełdowy funduszu inwestycyjnego</h1>
	<h2>Zmiana wartości jednostki</h2>
	<div id="chartWrapper">
		<h4>Proszę wybrać zakres dat:</h4>
		<input id="fromDate" type="text" placeholder="Proszę wybrać datę początkową" />
		<input id="toDate" type="text" placeholder="Proszę wybrać datę końcową" />
		
		<div class="canvas-holder">
			<canvas id="lineChart" ></canvas>
		</div>
		
	</div>
	<div class="tableWrapper">
		<div class="paging"></div>
		<table id="table" class="table">
			<thead>
				<tr>
					<th>id</th>
					<th>data</th>
					<th>wartość</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fund" items="${fundList}">
					<tr>
						<td>${fund.getId()}</td>
						<td>${fund.getParsedDate()}</td>
						<td>${fund.getValue()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging"></div>
	</div>
</body>
</html>