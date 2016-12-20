var services = angular.module('systemManager.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('systemManagerService', ['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/systemManager/checkRights");
	}
	
	this.findAllRestaurantManagers = function(){
		return $http.get("/systemManager/restaurantManager");
	}
	
	this.findAllFreeRestaurantManagers = function(){
		return $http.get("/systemManager/freeRestaurantManager");
	}
	
	this.saveRestaurantManager = function(restaurantManager){
		return $http.post("/systemManager/restaurantManager",restaurantManager);
	}
	
	this.findOneRestaurantManager = function(id){
		return $http.get("/systemManager/restaurantManager/"+id);
	}
	
	
	this.deleteRestaurantManager = function(id){
		return $http.delete("/systemManager/restaurantManager/"+id);
	}
	this.updateRestaurantManager = function(restaurantManager){
		return $http.put("/systemManager/restaurantManager/"+restaurantManager.id,restaurantManager);
	}
	
	
	
	
	
	
	
	
	
	this.findAllRestaurant = function(){
		return $http.get("/systemManager/restaurant");
	}
	
	this.findOneRestaurant = function(id){
		return $http.get("/systemManager/restaurant/"+id);
	}
	
	this.saveRestaurant = function(restaurant){
		return $http.post("/systemManager/restaurant",restaurant);
	}
	
	this.deleteRestaurant = function(id){
		return $http.delete("/systemManager/restaurant/"+id);
	}
	this.updateRestaurant = function(restaurant){
		return $http.put("/systemManager/restaurantManager/"+restaurant.id,restaurant);
	}
}]);
