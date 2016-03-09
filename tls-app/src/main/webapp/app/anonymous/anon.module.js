define([
   'angular',
   'anonymous/controller/AnonRootController.js'
], function (angular, AnonRootController) {
  console.debug('Configuring anon.module');
  angular.module('anon.module', [])
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('anon', {
        url: '/anon/',
        templateUrl: 'anonymous/view/anon.html',
        controller: AnonRootController,
        abstract: true
      })
      .state('anon.joborders', {
        url: 'joborders/{customerCode}?{customerName}',
        templateUrl: 'anonymous/view/joborders.html'
      });

    }]);
});