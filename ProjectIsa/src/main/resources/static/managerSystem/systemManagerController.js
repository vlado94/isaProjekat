var app = angular.module('systemManager.controllers', []);

app.controller('systemManagerController', ['$scope','systemManagerService', '$location',
	function ($scope, systemManagerService, $location) {
		function findAll() {
			systemManagerService.findAll().then(
				function (response) {
					$scope.systemMenagers = response.data;
				}
			);
		}
		findAll();
		
		$scope.save = function () {            
			systemManagerService.save($scope.systemManager).then(
				function (response) {
                    alert("Uspesno dodat.");
                    $scope.state = undefined;
                    $location.path('systemManager/list');
                },
                function (response) {
                    alert("Greska pri dodavanju.");
                }
            ); 	
		};
	    $scope.delete = function (systemManager){
	    	systemManagerService.delete(systemManager.id).then(
	    		function (response) {
	    		     $scope.systemMenagers.splice($scope.systemMenagers.indexOf(systemManager), 1);
			    },
		        function (response) {
		        	alert("Error while deleting component.");
		        }
	    	);
		};		 
}]);