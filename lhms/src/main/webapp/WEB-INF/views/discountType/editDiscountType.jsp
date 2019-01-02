<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="edit-discount-type-form" method="POST"
		commandName="discountType" onsubmit="editDiscountType(event)">
		<sf:input path="id" type="hidden" />
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
						<button class="btn btn-sm btn-warning">
							<i class="fa fa-edit"></i>
						</button>
					</td>


				</tr>

			</tbody>

		</table>
	</sf:form>
</div>


<script>
	function editDiscountType(event) {
		event.preventDefault();
		console.log("editDiscountType->fired");

		var data = $("#edit-discount-type-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/discountTypes/update"/>",
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