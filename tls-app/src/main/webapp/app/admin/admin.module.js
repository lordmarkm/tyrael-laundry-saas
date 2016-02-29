define([
   'angular',
   'admin/controller/AdminRootController',
   'admin/controller/AdminDashboardController',
   'admin/resolve/AdminDashboardResolve'
], function (angular, AdminRootController, AdminDashboardController,
    AdminDashboardResolve) {
  console.debug('Configuring admin.module');
  angular.module('admin.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.admin', {
        url: 'admin',
        template: '<ui-view></ui-view>',
        controller: AdminRootController,
        abstract: true
      }).state('default.admin.dashboard', {
        url: '',
        templateUrl: 'admin/view/dashboard.html',
        controller: AdminDashboardController,
        resolve: AdminDashboardResolve,
        access: 'ROLE_ADMIN'
      });
    }]);

});