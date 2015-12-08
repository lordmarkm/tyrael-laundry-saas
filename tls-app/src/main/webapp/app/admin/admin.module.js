define([
   'angular',
   'admin/controller/AdminRootController'
], function (angular, AdminRootController) {
  console.debug('Configuring admin.module');
  angular.module('admin.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.admin', {
        url: 'admin',
        templateUrl: 'admin/view/admin.html',
        controller: AdminRootController,
        abstract: true
      }).state('default.admin.dashboard', {
        url: '',
        templateUrl: 'admin/view/dashboard.html',
        access: 'ROLE_ADMIN'
      });
    }]);

});