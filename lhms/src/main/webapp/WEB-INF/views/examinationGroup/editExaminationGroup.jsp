<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<div>
	<sf:form id="edit-examination-group-form" method="POST"
		commandName="examinationGroup" onsubmit="editExaminationGroup(event)">
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
	function editExaminationGroup(event) {
		event.preventDefault();
		console.log("editExaminationGroup->fired");

		var data = $("#edit-examination-group-form").serializeJSON();
		console.log("data=", data);

		$.ajax({
			type : "POST",
			url : "<c:url value="/examinationGroups/update"/>",
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