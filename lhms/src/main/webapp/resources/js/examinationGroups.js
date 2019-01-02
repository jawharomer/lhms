function getAddingExaminationGroup() {
	console.log("getAddingExaminationGroup->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/examinationGroups/add",
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

function getEditingExaminationGroup(id) {
	console.log("getEditingExaminationGroup->fired");
	console.log("id=" + id);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/examinationGroups/edit/" + id,
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

function deleteExaminationGroup(id) {
	console.log("deleteExaminationGroup->fired");
	console.log("id=" + id);
	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + '/examinationGroups/delete/' + id,
			type : 'POST',
			success : function(response) {
				$("#modal-body").html(response);
				$("#modal").modal("show");
			},
			error : function(response) {
				$("#modal-body").html(response.responseText);
				$("#modal").modal("show");
			}
		});
	});

}