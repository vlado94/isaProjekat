var app = angular.module('restaurantManager.controllers', []);

app.controller('restaurantManagerController', ['$scope','restaurantManagerService', '$location',
	function ($scope, restaurantManagerService, $location) {
		function findAll() {
			restaurantManagerService.findAll().then(
				function (response) {
					$scope.restaurantManagers = response.data;
				}
			);
		}
		findAll();
		
		$scope.save = function () {            
			restaurantManagerService.save($scope.restaurantManager).then(
				function (response) {
                    alert("Uspesno dodat.");
                    $scope.state = undefined;
                    $location.path('restaurantManager/list');
                }
            ); 	
		};
	    $scope.delete = function (restaurantManager){
	    	restaurantManagerService.delete(restaurantManager.id).then(
	    		function (response) {
	    		     $scope.restaurantManagers.splice($scope.restaurantManager.indexOf(restaurantManager), 1);
			    }
		        
	    	);
		};		 
}]);