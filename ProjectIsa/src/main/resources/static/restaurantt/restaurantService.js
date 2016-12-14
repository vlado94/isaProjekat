var services = angular.module('restaurant.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('restaurantService', ['$http', function($http){
	
	this.findAll = function(){
		return $http.get("/restaurant");
	}
	
	this.findOne = function(id){
		return $http.get("/restaurant/"+id);
	}
	
	this.save = function(restaurant){
		return $http.post("/restaurant",restaurant);
	}
	
	this.update = function(restaurant){
		return $http.put("/restaurant/"+restaurant.id,restaurant);
	}
	
	this.delete = function(id){
		return $http.delete("/restaurant/"+id);
	}
}]);