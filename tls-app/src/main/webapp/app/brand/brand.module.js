define([
   'angular',
   'brand/controller/BrandRootController',
   'brand/controller/BrandListController',
   'brand/controller/BrandViewController',
   'brand/controller/BrandUpdateController',
   'brand/service/BrandService',
   'brand/resolve/BrandViewResolve',
   'brand/resolve/BrandUpdateResolve'
], function (angular, BrandRootController, BrandListController, BrandViewController, BrandUpdateController,
    BrandService,
    BrandViewResolve, BrandUpdateResolve) {

  console.debug('Configuring brand.module');
  angular.module('brand.module', [])
    .service('BrandService', BrandService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.brand', {
        url: 'brand',
        template: '<ui-view></ui-view>',
        controller: BrandRootController,
        abstract: true
      })
      .state('default.brand.list', {
        url: '',
        templateUrl: 'brand/view/list.html',
        controller: BrandListController,
        access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER']
      })
      .state('default.brand.add', {
        url: '/add',
        templateUrl: 'brand/view/update.html',
        controller: BrandUpdateController,
        resolve: BrandUpdateResolve,
        access: 'ROLE_ADMIN'
      })
      .state('default.brand.update', {
        url: '/update/{brandCode}/{urlSlug}',
        templateUrl: 'brand/view/update.html',
        controller: BrandUpdateController,
        resolve: BrandUpdateResolve,
        access: 'ROLE_ADMIN'
      })
      .state('default.brand.view', {
        url: '/{brandCode}/{urlSlug}',
        templateUrl: 'brand/view/view.html',
        controller: BrandViewController,
        resolve: BrandViewResolve
      });
    }]);

});