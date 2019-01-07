// Angular
app = angular.module("addPatientVisit", []);

app
		.controller(
				'addPatientVisit',
				function($scope, $http) {

					$scope.patientVisit = {};
					$scope.discountTypes = [];

					$scope.init = function() {
						console.log("init->fired");
						console.log("jsonPatientVisit=" + jsonPatientVisit);
						console.log("jsonDiscountTypes=" + jsonDiscountTypes);

						$scope.patientVisit = JSON.parse(jsonPatientVisit);
						$scope.discountTypes = JSON.parse(jsonDiscountTypes);

					};

					$scope.totalPrice = function() {
						var total = 0;
						for (var i = 0; i < $scope.patientVisit.patientExaminations.length; i++) {
							total = total
									+ $scope.patientVisit.patientExaminations[i].price;
						}
						return total;
					}

					$scope.selectDiscountType = function() {
						if ($scope.patientVisit.discountType.id) {
							var discountType = $scope.discountTypes
									.filter(function(item) {
										return item.id == $scope.patientVisit.discountType.id
									})[0];

							$scope.patientVisit.discountType.fix = discountType.fix;
							$scope.patientVisit.discountType.limit = discountType.limit;
							$scope.patientVisit.discountType.name = discountType.name;
						} else {
							$scope.patientVisit.discountType = null;
						}
						$scope.patientVisit.discountAmount = "";
					}

					$scope.save = function() {
						console.log("save->fired");
						console
								.log("$scope.patientVisit=",
										$scope.patientVisit);
						$http({
							method : 'POST',
							data : $scope.patientVisit,
							url : $$ContextURL + '/patientVisits/payment'
						}).then(function(response) {
							console.log(response);
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						}, function(response) {
							$("#modal-body").html(response.data);
							$("#modal").modal("show");
						});

					}

				});
