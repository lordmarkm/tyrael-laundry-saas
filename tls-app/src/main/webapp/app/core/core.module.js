define([
   'angular',
   'core/controller/RootController',
   'core/controller/AuthenticationController',
   'core/service/AuthenticationService',
   'core/service/LoginService',
   'core/service/JobItemTypeService',
   'core/service/GenericConfirmService',
   'core/service/DashboardService'
], function (angular, RootController, AuthenticationController, AuthenticationService, LoginService,
    JobItemTypeService,
    GenericConfirmService, DashboardService) {
  console.debug('Configuring core.module');
  angular.module('core.module', [])
    .service('AuthenticationService', AuthenticationService)
    .service('LoginService', LoginService)
    .service('JobItemTypeService', JobItemTypeService)
    .service('confirm', GenericConfirmService)
    .service('DashboardService', DashboardService)
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

    //Try to have the oader for every http
    .config(['$httpProvider', function ($httpProvider) {
      $httpProvider.interceptors.push(['$q', '$rootScope', function ($q, $rootScope) {
        var numLoadings = 0;
        return {
          request: function (config) {
            numLoadings++;
            $rootScope.$broadcast("loader_show");
            return config || $q.when(config);
          },
          response: function (response) {
            if ((--numLoadings) === 0) {
              $rootScope.$broadcast("loader_hide");
            }
            return response;
          },
          responseError: function (response) {
            if (!(--numLoadings)) {
              $rootScope.$broadcast("loader_hide");
            }
            return $q.reject(response);
          }
        };
      }]);
    }])

    //Scroll to top on location change
    .run(['$rootScope', '$window', function ($rootScope, $window) {
      $rootScope.$on("$locationChangeSuccess", function() {
        $window.scrollTo(0,0);
      });
    }]);

});