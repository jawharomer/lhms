<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<div>
	<sf:form id="add-visit-payment-form" method="POST"
		commandName="patientVisit" onsubmit="addVisitPayment(event)">
		<sf:input type="hidden" path="id" />
		<table class="w-100">
			<tbody>

				<tr>
					<td>Payment Amount</td>
					<td><sf:input type="number"
							cssClass="form-control form-control-sm" path="totalPayment" /></td>
				</tr>
				<tr>
					<td>
						<button class="btn btn-sm btn-success">
							<i class="fa fa-plus"></i>
						</button>
					</td>


				</tr>

			</tbody>

		</table>


	</sf:form>

</div>

<script>
	function addVisitPayment(event) {
		event.preventDefault();
		console.log("addVisitPayment->fired");

		var data = $("#add-visit-payment-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/patientVisits/payment"/>",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	}
</script>