'use strict';

angular.module('routerApp', ['ui.router', 
	'systemManager.services', 'systemManager.controllers', 
	'restaurantManager.services', 'restaurantManager.controllers',
	'restaurant.services', 'restaurant.controllers'])
.config(function($stateProvider, $urlRouterProvider) {
        
        $urlRouterProvider.otherwise('/home');
        
        $stateProvider

        .state('home', {
        	url : '/home',
          	templateUrl : 'home.html'
         })
         .state('systemManager', {
        	url : '/systemManager',
          	templateUrl : 'managerSystem/systemManagerPartial.html',
            controller : 'systemManagerController'
         })
        .state('systemManager.list', {
        	url : '/list',
          	templateUrl : 'managerSystem/systemManagerList.html'
        })
         .state('systemManager.edit', {
        	url : '/edit',
          	templateUrl : 'managerSystem/systemManagerEdit.html'
         })
         .state('systemManager.new', {
        	url : '/new',
        	templateUrl : 'managerSystem/systemManagerNew.html'
        })
        
        .state('restaurantManager', {
        	url : '/restaurantManager',
          	templateUrl : 'managerRestaurant/restaurantManagerPartial.html',
            controller : 'restaurantManagerController'
         })
         .state('restaurantManager.list', {
        	url : '/list',
          	templateUrl : 'managerRestaurant/restaurantManagerList.html'
        })
		.state('restaurantManager.new', {
			url : '/new',
			templateUrl : 'managerRestaurant/restaurantManagerNew.html'
		})
	        
        
        
        .state('restaurant', {
        	url : '/restaurant',
          	templateUrl : 'restaurantt/restaurantPartial.html',
            controller : 'restaurantController'
         })
         .state('restaurant.list', {
        	url : '/list',
          	templateUrl : 'restaurantt/restaurantList.html'
        })
        .state('restaurant.new', {
        	url : '/new',
        	templateUrl : 'restaurantt/restaurantNew.html'
        })
        .state('restaurant.profile', {
        	url : '/profile',
        	templateUrl : 'restaurantt/restaurantDetail.html'
        });
});