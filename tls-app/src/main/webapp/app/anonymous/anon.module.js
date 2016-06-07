define([
   'angular',
   'anonymous/controller/AnonRootController.js',
   'anonymous/controller/AnonJobOrderListController.js',
   'anonymous/controller/AnonJobOrderViewController.js',
   'joborder/resolve/JobOrderViewResolve.js'
], function (angular, AnonRootController, AnonJobOrderListController, AnonJobOrderViewController, JobOrderViewResolve) {
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
        templateUrl: 'anonymous/view/joborders.html',
        controller: AnonJobOrderListController,
        access: 'anonymous'
      })
      .state('anon.joborder_view', {
        url: 'joborder/{joborderCode}/{urlSlug}',
        templateUrl: 'joborder/view/view.html',
        controller: AnonJobOrderViewController,
        resolve: JobOrderViewResolve,
        access: 'anonymous'
      })
      .state('anon.joborder_deliver', {
        url: 'joborder/deliver',
        template: 'Automated delivery requests are in progress. At the moment, please text 09235981954.',
        access: 'anonymous'
      });

    }]);
});