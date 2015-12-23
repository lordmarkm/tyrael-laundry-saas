define([
   'angular',
   'joborder/controller/JobOrderRootController',
   'joborder/controller/JobOrderListController',
   'joborder/controller/JobOrderViewController',
   'joborder/controller/JobOrderUpdateController',
   'joborder/service/JobOrderService',
   'joborder/service/JobOrderStatusService',
   'joborder/resolve/JobOrderListResolve',
   'joborder/resolve/JobOrderViewResolve',
   'joborder/resolve/JobOrderUpdateResolve'
], function (angular, JobOrderRootController, JobOrderListController, JobOrderViewController, JobOrderUpdateController,
    JobOrderService, JobOrderStatusService,
    JobOrderListResolve, JobOrderViewResolve, JobOrderUpdateResolve) {

  console.debug('Configuring joborder.module');
  angular.module('joborder.module', [])
    .service('JobOrderService', JobOrderService)
    .service('JobOrderStatusService', JobOrderStatusService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.joborder', {
        url: 'joborders',
        template: '<ui-view></ui-view>',
        controller: JobOrderRootController,
        abstract: true
      })
      .state('default.joborder.list', {
        url: '',
        templateUrl: 'joborder/view/list.html',
        controller: JobOrderListController,
        resolve: JobOrderListResolve
      })
      .state('default.joborder.add', {
        url: '/add',
        templateUrl: 'joborder/view/update.html',
        controller: JobOrderUpdateController,
        resolve: JobOrderUpdateResolve
      })
      .state('default.joborder.update', {
        url: '/update/{joborderCode}/{urlSlug}',
        templateUrl: 'joborder/view/update.html',
        controller: JobOrderUpdateController,
        resolve: JobOrderUpdateResolve
      })
      .state('default.joborder.view', {
        url: '/{joborderCode}/{urlSlug}',
        templateUrl: 'joborder/view/view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve
      });
    }]);

});