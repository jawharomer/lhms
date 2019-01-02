<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="add-discount-type-form" method="POST"
		commandName="discountType" onsubmit="addDiscountType(event)">
		<table class="w-100">
			<tbody>
				<tr>
					<td>Name</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="name" /></td>
					<td><sf:errors path="name" /></td>
				</tr>
				<tr>
					<td>Limit</td>
					<td><sf:input cssClass="form-control form-control-sm"
							path="limit" /></td>
					<td><sf:errors path="limit" /></td>
				</tr>

				<tr>
					<td>Fix</td>
					<td><sf:checkbox path="fix" value="true" /></td>
					<td><sf:errors path="fix" /></td>
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
	function addDiscountType(event) {
		event.preventDefault();
		console.log("addDiscountType->fired");

		var data = $("#add-discount-type-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/discountTypes/add"/>",
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