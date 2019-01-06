<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	var reportTitle = '<fmt:formatDate value="${from}" pattern="yyyy-MM-dd"/>_To_<fmt:formatDate value="${to}" pattern="yyyy-MM-dd"/>';
</script>


<div>
	<div class="py-2">
		<h3>Patient Visits</h3>
	</div>

	<div>
		<form action="<c:url value="/patientVisits" />">
			<table>
				<tr>
					<td class="text-left">From</td>
					<td><input readonly class="form-control" id="from" name="from"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${from}" />" /></td>
				</tr>

				<tr>
					<td class="text-left">To</td>
					<td><input readonly class="form-control" id="to" name="to"
						value="<fmt:formatDate pattern = "yyyy-MM-dd"  
         value = "${to}" />" /></td>
				</tr>
				<tr>
					<td><input class="btn btn-outline-info" type="submit"
						value="View" /></td>
				</tr>
			</table>
		</form>


	</div>

	<hr>


	<table id="patient-visits-table" class="display nowrap">
		<thead>
			<tr>
				<th>#</th>
				<th>FullName</th>
				<th>Time</th>
				<th>T-Price</th>
				<th>T-Payment</th>
				<th>T-Unpaid</th>
				<th>IsDone</th>
				<th>F</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="sumTotalPayment" value="${0}" />

			<c:forEach items="${patientVisits}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.patient.fullName}</td>
					<td><fmt:formatDate value="${item.time}"
							pattern="yyyy-MM-dd hh:mm:ss" /></td>
					<c:set var="totalPrice" value="${0}" />
					<c:forEach items="${item.patientExaminations}" var="eItem">
						<c:set var="totalPrice" value="${totalPrice+eItem.price}" />
					</c:forEach>
					<td><fmt:formatNumber maxFractionDigits="3">${totalPrice}</fmt:formatNumber>
					</td>
					<td>
						<button class="btn btn-sm btn-info"
							onclick="getAddingPayment(${item.id})">
							<i class="fa fa-money"></i>
						</button> <fmt:formatNumber maxFractionDigits="3">
					${item.totalPayment}
					</fmt:formatNumber> <c:set var="sumTotalPayment"
							value="${sumTotalPayment+item.totalPayment}" />
					</td>
					<td><fmt:formatNumber maxFractionDigits="3">
					${totalPrice-totalPayment}
					</fmt:formatNumber></td>

					<td>${item.done}</td>
					<td>
						<div>
							<a class="btn btn-sm btn-warning"
								href="<c:url value="/patientVisits/edit/"/>${item.id}"> <i
								class="fa fa-edit"></i>
							</a>

							<button class="btn btn-sm btn-danger"
								onclick="deletePatientVisit(${item.id})">
								<i class="fa fa-times"></i>
							</button>

						</div>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="p-1">
		Sum Total Payment :
		<fmt:formatNumber maxFractionDigits="3"> ${sumTotalPayment}
	</fmt:formatNumber>
	</div>

</div>