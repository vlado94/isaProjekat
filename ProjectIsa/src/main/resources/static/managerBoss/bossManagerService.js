var services = angular.module('bossManager.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('bossManagerService', ['$http', function($http){
	
	this.checkRights = function(){
		return $http.get("/bossManager/checkRights");
	}
	
	this.findAll = function(){
		return $http.get("/bossManager");
	}
	
	this.findOne = function(id){
		return $http.get("/bossManager/"+id);
	}
	
	this.save = function(systemManager){
		return $http.post("/bossManager",systemManager);
	}
	
	this.update = function(systemManager){
		return $http.put("/bossManager/"+systemManager.id,systemManager);
	}
	
	this.delete = function(id){
		return $http.delete("/bossManager/"+id);
	}
}]);
