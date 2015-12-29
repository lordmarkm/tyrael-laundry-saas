define(function () {
  return ['$scope', '$state', 'toaster', 'inventoryItem', 'branches', 'InventoryItemService', 'InventoryItemTypeService',
          function ($scope, $state, toaster, inventoryItem, branches, InventoryItemService, InventoryItemTypeService) {
    $scope.inventoryItem = inventoryItem;
    $scope.branches = branches;

    $scope.updateItemTypes = function () {
      $scope.inventoryItemTypes = InventoryItemTypeService.query({branchCode: inventoryItem.branchCode});
    };

    if (!inventoryItem.branchCode && angular.isArray(branches) && branches.length) {
      inventoryItem.branchCode = branches[0].code;
      $scope.updateItemTypes();
    }

    $scope.setInventoryItemTypeProperties = function (iit) {
      $scope.inventoryItem.inventoryItemTypeCode = iit.code;
      $scope.inventoryItem.forSale = iit.forSale;
      if (iit.defaultBuyingPrice) {
        $scope.inventoryItem.buyingPrice = iit.defaultBuyingPrice;
      }
      if (iit.defaultSellingPrice) {
        $scope.inventoryItem.sellingPrice = iit.defaultSellingPrice;
      }
      if (iit.defaultSupplierName) {
        $scope.inventoryItem.supplierName = iit.defaultSupplierName;
      }
    };

    $scope.save = function (valid) {
      if (!valid) {
        toaster.pop('error', 'Invalid submit', 'Inventory item form is invalid');
      }
      InventoryItemService.save($scope.inventoryItem, function (saved) {
        $state.go('default.inv_item.view', {invItemCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});