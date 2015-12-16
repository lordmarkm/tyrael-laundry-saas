define([
   'angular',
   'brandmgr/controller/BrandMgrRootController'
], function (angular, BrandMgrRootController) {
  console.debug('Configuring brandmgr.module');
  angular.module('brandmgr.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.brandmgr', {
        url: 'brandmgr',
        template: '<ui-view></ui-view>',
        controller: BrandMgrRootController,
        abstract: true
      }).state('default.brandmgr.dashboard', {
        url: '',
        templateUrl: 'brandmgr/view/dashboard.html',
        access: 'ROLE_BRAND_MANAGER'
      });
    }]);

});