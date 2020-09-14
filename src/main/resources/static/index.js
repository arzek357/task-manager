(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'about/about.html',
                controller: 'aboutController'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/projects', {
                templateUrl: 'projects/projects.html',
                controller: 'projectsController'
            })
            .when('/in_project/:id', {
                templateUrl: 'in_project/in_project.html',
                controller: 'in_projectController'
            })
            .when('/in_task/:id', {
                templateUrl: 'in_task/in_task.html',
                controller: 'in_taskController'
            })
            .when('/register', {
                templateUrl: 'register/register.html',
                controller: 'registerController'
            });

        $httpProvider.interceptors.push(function($q, $location) {
            return {
                'responseError': function(rejection, $localStorage, $http) {
                    var defer = $q.defer();
                    if (rejection.status == 401 || rejection.status == 403) {
                        console.log('error: 401-403');
                        $location.path('/auth');
                        delete $localStorage.currentUser;
                        $http.defaults.headers.common.Authorization = '';
                        // var answer = JSON.parse(rejection.data);
                        // window.alert(answer.message);
                    }
                    defer.reject(rejection);
                    return defer.promise;
                }
            };
        });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
        }
    }
})();
