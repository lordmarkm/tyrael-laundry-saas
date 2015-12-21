define([
   'angular',
   'customer/controller/CustomerRootController',
   'customer/controller/CustomerListController',
   'customer/controller/CustomerViewController',
   'customer/controller/CustomerUpdateController',
   'customer/service/CustomerService',
   'customer/resolve/CustomerListResolve',
   'customer/resolve/CustomerViewResolve',
   'customer/resolve/CustomerUpdateResolve'
], function (angular, CustomerRootController, CustomerListController, CustomerViewController, CustomerUpdateController,
    CustomerService,
    CustomerListResolve, CustomerViewResolve, CustomerUpdateResolve) {

  console.debug('Configuring customer.module');
  angular.module('customer.module', [])
    .service('CustomerService', CustomerService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.customer', {
        url: 'customers',
        template: '<ui-view></ui-view>',
        controller: CustomerRootController,
        abstract: true
      })
      .state('default.customer.list', {
        url: '',
        templateUrl: 'customer/view/list.html',
        controller: CustomerListController,
        resolve: CustomerListResolve
      })
      .state('default.customer.add', {
        url: '/add',
        templateUrl: 'customer/view/update.html',
        controller: CustomerUpdateController,
        resolve: CustomerUpdateResolve
      })
      .state('default.customer.update', {
        url: '/update/{customerCode}/{urlSlug}',
        templateUrl: 'customer/view/update.html',
        controller: CustomerUpdateController,
        resolve: CustomerUpdateResolve
      })
      .state('default.customer.view', {
        url: '/{customerCode}/{urlSlug}',
        templateUrl: 'customer/view/view.html',
        controller: CustomerViewController,
        resolve: CustomerViewResolve
      });
    }]);

});