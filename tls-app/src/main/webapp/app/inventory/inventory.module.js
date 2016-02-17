define([
   'angular',
   'inventory/controller/InventoryItemTypeRootController',
   'inventory/controller/InventoryItemTypeListController',
   'inventory/controller/InventoryItemTypeViewController',
   'inventory/controller/InventoryItemTypeUpdateController',
   'inventory/service/InventoryItemTypeService',
   'inventory/resolve/InventoryItemTypeListResolve',
   'inventory/resolve/InventoryItemTypeViewResolve',
   'inventory/resolve/InventoryItemTypeUpdateResolve',

   'inventory/controller/InventoryItemRootController',
   'inventory/controller/InventoryItemListController',
   'inventory/controller/InventoryItemViewController',
   'inventory/controller/InventoryItemUpdateController',
   'inventory/service/InventoryItemService',
   'inventory/resolve/InventoryItemListResolve',
   'inventory/resolve/InventoryItemViewResolve',
   'inventory/resolve/InventoryItemUpdateResolve',
   'inventory/service/ShoppingCartService',
   'inventory/controller/CheckoutController',
   'inventory/service/SalesHeaderService',

   'inventory/controller/SalesHeaderListController',
   'inventory/controller/SalesHeaderViewController',
   'inventory/resolve/SalesHeaderViewResolve',
   'inventory/resolve/SalesHeaderListResolve'
], function (angular, InventoryItemTypeRootController, InventoryItemTypeListController, InventoryItemTypeViewController, InventoryItemTypeUpdateController,
    InventoryItemTypeService,
    InventoryItemTypeListResolve, InventoryItemTypeViewResolve, InventoryItemTypeUpdateResolve,

    InventoryItemRootController, InventoryItemListController, InventoryItemViewController, InventoryItemUpdateController,
    InventoryItemService,
    InventoryItemListResolve, InventoryItemViewResolve, InventoryItemUpdateResolve,

    ShoppingCartService, CheckoutController, SalesHeaderService,

    SalesHeaderListController, SalesHeaderViewController, SalesHeaderViewResolve, SalesHeaderListResolve) {

  console.debug('Configuring inventory.module');
  angular.module('inventory.module', [])
    .service('InventoryItemService', InventoryItemService)
    .service('InventoryItemTypeService', InventoryItemTypeService)
    .service('ShoppingCartService', ShoppingCartService)
    .service('SalesHeaderService', SalesHeaderService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.inv_item', {
        url: 'inv-item',
        template: '<ui-view></ui-view>',
        controller: InventoryItemRootController,
        abstract: true
      })
      .state('default.inv_item.list', {
        url: '',
        templateUrl: 'inventory/view/list.html',
        controller: InventoryItemListController,
        resolve: InventoryItemListResolve
      })
      .state('default.inv_item.view', {
        url: '/{invItemCode}/{urlSlug}',
        templateUrl: 'inventory/view/view.html',
        controller: InventoryItemViewController,
        resolve: InventoryItemViewResolve
      })
      .state('default.inv_item.add', {
        url: '/add',
        templateUrl: 'inventory/view/update.html',
        controller: InventoryItemUpdateController,
        resolve: InventoryItemUpdateResolve
      })
      .state('default.inv_item.update', {
        url: '/update/{invItemCode}/{urlSlug}',
        templateUrl: 'inventory/view/update.html',
        controller: InventoryItemUpdateController,
        resolve: InventoryItemUpdateResolve
      })
      .state('default.inv_item_type', {
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
        controller: InventoryItemTypeListController,
        resolve: InventoryItemTypeListResolve
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
      })

      //checkout shopping cart
      .state('default.checkout', {
        url: 'checkout',
        templateUrl: 'inventory/view/checkout.html',
        controller: CheckoutController
      })

      //view sale
      .state('default.sales_header', {
        url: 'sales-header',
        template: '<ui-view></ui-view>',
        abstract: true
      })
      .state('default.sales_header.list', {
        url: '',
        templateUrl: 'inventory/view/sales-header-list.html',
        controller: SalesHeaderListController,
        resolve: SalesHeaderListResolve
      })
      .state('default.sales_header.byitem', {
        url: '/item/{itemCode}?{itemName}',
        templateUrl: 'inventory/view/sales-header-list.html',
        controller: SalesHeaderListController,
        resolve: SalesHeaderListResolve
      })
      .state('default.sales_header.view', {
        url: '/{id}',
        templateUrl: 'inventory/view/sales-header-view.html',
        controller: SalesHeaderViewController,
        resolve: SalesHeaderViewResolve
      });
    }]);

});