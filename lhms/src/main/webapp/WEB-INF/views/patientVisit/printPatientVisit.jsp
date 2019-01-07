<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div id="section-to-print">
	<table class="border-bottom">
		<tr>
			<td><img src="<c:url value="/resources/img/logo.png" />"
				height="100" alt=""></td>

			<td class="text-center"><h5>Bahceci labratory</h5></td>
			<td class="text-right"><img
				src="<c:url value="/resources/img/logo.png" />" height="100" alt=""></td>

		</tr>
	</table>
	<div class="container-fluid">
		<div class="row border">
			<div class="col-6">
				<table class=" ">
					<tr>
						<td>Lab#</td>
						<td>${patientVisit.id }</td>
					</tr>
					<tr>
						<td>Time</td>
						<td>${patientVisit.time}</td>
					</tr>
				</table>
			</div>

			<div class="col-6">
				<table class=" ">
					<tr>
						<td>Patient Id</td>
						<td>${patientVisit.patient.id }</td>
					</tr>
					<tr>
						<td>Name</td>
						<td>${patientVisit.patient.fullName }</td>
					</tr>
					<tr>
						<td>DOB</td>
						<td>${patientVisit.patient.birthDate }</td>
					</tr>
				</table>
			</div>

		</div>
	</div>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Name</th>
				<th>Result</th>
				<th>Unit</th>
				<th>Normal</th>
			</tr>
			<c:forEach items="${patientVisit.patientExaminations}" var="item">
				<tr>
					<td style="max-width: 200px">${item.examination.name}<c:if
							test="${item.examination.note!=null}">
							<pre style="border: none; font-weight: bold;">${item.examination.note}</pre>
						</c:if>
					</td>
					<td style="max-width: 200px">${item.result}</td>
					<td>${item.examination.unit}</td>
					<td>${item.examination.normal}</td>
				</tr>
			</c:forEach>
		</thead>
	</table>
</div>

