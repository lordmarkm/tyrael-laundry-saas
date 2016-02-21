define([
   'angular',
   'acctspayable/controller/AcctsPayableRootController',
   'acctspayable/controller/AcctsPayableListController',
   'acctspayable/controller/AcctsPayableViewController',
   'acctspayable/controller/AcctsPayableUpdateController',
   'acctspayable/service/AcctsPayableService',
   'acctspayable/service/AcctsPaymentService',
   'acctspayable/resolve/AcctsPayableListResolve',
   'acctspayable/resolve/AcctsPayableViewResolve',
   'acctspayable/resolve/AcctsPayableUpdateResolve'
], function (angular, AcctsPayableRootController, AcctsPayableListController, AcctsPayableViewController, AcctsPayableUpdateController,
    AcctsPayableService, AcctsPaymentService,
    AcctsPayableListResolve, AcctsPayableViewResolve, AcctsPayableUpdateResolve) {

  console.debug('Configuring acctspayable.module');
  angular.module('acctspayable.module', [])
    .service('AcctsPayableService', AcctsPayableService)
    .service('AcctsPaymentService', AcctsPaymentService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.acctspayable', {
        url: 'accounts-payable',
        template: '<ui-view></ui-view>',
        controller: AcctsPayableRootController,
        abstract: true,
        access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER']
      })
      .state('default.acctspayable.list', {
        url: '',
        templateUrl: 'acctspayable/view/list.html',
        controller: AcctsPayableListController,
        resolve: AcctsPayableListResolve
      })
      .state('default.acctspayable.add', {
        url: '/add',
        templateUrl: 'acctspayable/view/update.html',
        controller: AcctsPayableUpdateController,
        resolve: AcctsPayableUpdateResolve
      })
      .state('default.acctspayable.update', {
        url: '/update/{apCode}/{urlSlug}',
        templateUrl: 'acctspayable/view/update.html',
        controller: AcctsPayableUpdateController,
        resolve: AcctsPayableUpdateResolve
      })
      .state('default.acctspayable.view', {
        url: '/{apCode}/{urlSlug}',
        templateUrl: 'acctspayable/view/view.html',
        controller: AcctsPayableViewController,
        resolve: AcctsPayableViewResolve
      });
    }]);

});