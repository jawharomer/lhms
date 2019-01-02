function getAddingDiscountType() {
	console.log("getAddingDiscountType->fired");
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/discountTypes/add",
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

function getEditingDiscountType(id) {
	console.log("getEditingDiscountType->fired");
	console.log("discountTypeId=" + id);
	$.ajax({
		type : "GET",
		url : $$ContextURL + "/discountTypes/edit/" + id,
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

function deleteDiscountType(id) {
	console.log("deleteDiscountType->fired");
	console.log("id=" + id);
	$.when(cusConfirm()).done(function() {
		$.ajax({
			url : $$ContextURL + '/discountTypes/delete/' + id,
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