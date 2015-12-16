define([
   'angular',
   'admin/controller/AdminRootController'
], function (angular, AdminRootController) {
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
        access: 'ROLE_ADMIN'
      });
    }]);

});