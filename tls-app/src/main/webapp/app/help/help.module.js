define([
   'angular',
   'help/controller/HelpRootController.js'
], function (angular, HelpRootController) {
  console.debug('Configuring help.module');
  angular.module('help.module', [])
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('help', {
        url: '/help',
        templateUrl: 'help/view/help.html',
        controller: HelpRootController,
        abstract: true
      })
      .state('help.index', {
        url: '/index',
        templateUrl: 'help/view/index.html'
      })

      //customer
      .state('help.customer', {
        url: '/customer',
        templateUrl: 'help/view/customer_index.html'
      })
      .state('help.customer_list', {
        url: '/customer-list',
        templateUrl: 'help/view/customer_list.html'
      })
      .state('help.customer_create', {
        url: '/customer-create-or-update',
        templateUrl: 'help/view/customer_create_update.html'
      })
      .state('help.customer_delete', {
        url: '/customer-delete',
        templateUrl: 'help/view/customer_delete.html'
      })
      .state('help.customer_filter_job_orders', {
        url: '/customer-filter-job-orders',
        templateUrl: 'help/view/customer_filter_job_orders.html'
      });

    }]);
});