define([
   'angular',
   'brand/controller/BrandRootController',
   'brand/controller/BrandListController',
   'brand/controller/BrandViewController',
   'brand/service/BrandService',
], function (angular, BrandRootController, BrandListController, BrandViewController, BrandService) {
  console.debug('Configuring brand.module');
  angular.module('brand.module', [])
    .service('BrandService', BrandService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.brand', {
        url: '/brand',
        template: '<ui-view></ui-view>',
        controller: BrandRootController,
        abstract: true
      })
      .state('default.brand.list', {
        url: '',
        templateUrl: 'brand/view/list.html',
        controller: BrandListController,
        access: 'ROLE_ADMIN'
      })
      .state('default.brand.view', {
        url: '/{brand-',
        templateUrl: 'core/view/login.html',
        controller: AuthenticationController
      });
    }]);

});