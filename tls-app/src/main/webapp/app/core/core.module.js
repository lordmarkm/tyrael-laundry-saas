define([
   'angular',
   'core/controller/RootController',
   'core/controller/AuthenticationController',
   'core/service/AuthenticationService',
   'core/service/LoginService'
], function (angular, RootController, AuthenticationController, AuthenticationService, LoginService) {
  console.debug('Configuring core.module');
  angular.module('core.module', [])
    .service('AuthenticationService', AuthenticationService)
    .service('LoginService', LoginService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

      $urlRouterProvider
        .when('', '/');

      $stateProvider.state('default', {
        url: '/',
        templateUrl: 'core/view/default.html',
        controller: RootController,
        abstract: true
      })
      .state('default.login', {
        url: 'login?msg',
        templateUrl: 'core/view/login.html',
        controller: AuthenticationController
      });
    }])

    //Scroll to top on location change
    .run(['$rootScope', '$window', function ($rootScope, $window) {
      $rootScope.$on("$locationChangeSuccess", function() {
        $window.scrollTo(0,0);
      });
    }]);

});