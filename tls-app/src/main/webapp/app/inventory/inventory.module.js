define([
   'angular',
   'inventory/controller/InventoryItemTypeRootController',
   'inventory/controller/InventoryItemTypeListController',
   'inventory/controller/InventoryItemTypeViewController',
   'inventory/controller/InventoryItemTypeUpdateController',
   'inventory/service/InventoryItemTypeService',
   'inventory/resolve/InventoryItemTypeViewResolve',
   'inventory/resolve/InventoryItemTypeUpdateResolve',

   'inventory/controller/InventoryItemRootController',
   'inventory/controller/InventoryItemListController',
   'inventory/controller/InventoryItemViewController',
   'inventory/controller/InventoryItemUpdateController',
   'inventory/service/InventoryItemService',
   'inventory/resolve/InventoryItemViewResolve',
   'inventory/resolve/InventoryItemUpdateResolve'
], function (angular, InventoryItemTypeRootController, InventoryItemTypeListController, InventoryItemTypeViewController, InventoryItemTypeUpdateController,
    InventoryItemTypeService,
    InventoryItemTypeViewResolve, InventoryItemTypeUpdateResolve,

    InventoryItemRootController, InventoryItemListController, InventoryItemViewController, InventoryItemUpdateController,
    InventoryItemService,
    InventoryItemViewResolve, InventoryItemUpdateResolve) {

  console.debug('Configuring inventory.module');
  angular.module('inventory.module', [])
    .service('InventoryItemTypeService', InventoryItemTypeService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.inv_item_type', {
        url: 'inv-item-type',
        template: '<ui-view></ui-view>',
        controller: InventoryItemTypeRootController,
        data: {
          access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER']
        },
        abstract: true
      })
      .state('default.inv_item_type.list', {
        url: '',
        templateUrl: 'inventory/view/type-list.html',
        controller: InventoryItemTypeListController
      })
      .state('default.inv_item_type.add', {
        url: '/add',
        templateUrl: 'inventory/view/type-update.html',
        controller: InventoryItemTypeUpdateController,
        resolve: InventoryItemTypeUpdateResolve
      })
      .state('default.inv_item_type.update', {
        url: '/update/{invItemTypeCode}/{urlSlug}',
        templateUrl: 'inventory/view/type-update.html',
        controller: InventoryItemTypeUpdateController,
        resolve: InventoryItemTypeUpdateResolve
      })
      .state('default.inv_item_type.view', {
        url: '/{invItemTypeCode}/{urlSlug}',
        templateUrl: 'inventory/view/type-view.html',
        controller: InventoryItemTypeViewController,
        resolve: InventoryItemTypeViewResolve
      });
    }]);

});