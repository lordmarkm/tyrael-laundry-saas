define([
   'angular',
   'servicetype/controller/PriceManagementController',
   'servicetype/service/ServiceTypeService',
   'servicetype/resolve/PriceManagementResolve',
], function (angular, PriceManagementController,
    ServiceTypeService,
    PriceManagementResolve) {

  console.debug('Configuring servicetype.module');
  angular.module('servicetype.module', [])
    .service('ServiceTypeService', ServiceTypeService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.pricemanagement', {
        url: 'price-mgt',
        templateUrl: 'servicetype/view/price-management.html',
        controller: PriceManagementController,
        resolve: PriceManagementResolve,
        access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER']
      });
    }]);

});