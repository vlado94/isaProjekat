var app = angular.module('systemManager.controllers', []);

app.controller('systemManagerController', ['$scope','systemManagerService','$location',
	function ($scope, systemManagerService, $location) {
		function checkRights() {
			systemManagerService.checkRights().then(
				function (response) {
					if(response.data === 'true')
						findAll();
					else {
					    $location.path('login');
					    alert("Access denied!");
				    }
				}
			);
		}
		checkRights();
		
		
		function findAll() {
			systemManagerService.findAllRestaurantManagers().then(
				function (response) {
					$scope.restaurantManagers = response.data;
				}
			);
			systemManagerService.findAllRestaurant().then(
					function (response) {
						$scope.restaurants = response.data;
					}
			);
		}
		
		$scope.findAllFreeRestaurantManagers = function () {            
			systemManagerService.findAllFreeRestaurantManagers().then(
				function (response) {
					$scope.freeRestaurantManagers = response.data;
				}
			); 	
		};
		
		$scope.saveManager = function () {            
			systemManagerService.saveRestaurantManager($scope.restaurantManager).then(
				function (response) {
                    alert("Uspesno dodat.");
                    findAll();
                    $scope.state = undefined;
                    $location.path('loggedIn/systemManager/list');
                },
                function (response) {
                    alert("Greska pri dodavanju.");
                }
            ); 	
		};
		
		$scope.deleteRestaurantManager = function (restaurantManager){
	    	systemManagerService.deleteRestaurantManager(restaurantManager.id).then(
	    		function (response) {
	    		     $scope.restaurantManagers.splice($scope.restaurantMenagers.indexOf(restaurantManager), 1);
			    },
		        function (response) {
		        	alert("Greska pri brisanju menazdera.");
		        }
	    	);
		};
		
		$scope.saveRestaurant = function () {            
			systemManagerService.saveRestaurant($scope.restaurant).then(
				function (response) {
                    alert("Uspesno dodat.");
                    findAll();
                    $scope.state = undefined;
                    $location.path('loggedIn/systemManager/list');
                },
                function (response) {
                    alert("Greska pri dodavanju.");
                }
            ); 	
		};
		
		$scope.deleteRestaurant = function (restaurant){
	    	systemManagerService.deleteRestaurant(restaurant.id).then(
	    		function (response) {
	    		     $scope.restaurants.splice($scope.restaurants.indexOf(restaurant), 1);
			    },
		        function (response) {
		        	alert("Greska pri brisanju restorana.");
		        }
	    	);
		};
}]);