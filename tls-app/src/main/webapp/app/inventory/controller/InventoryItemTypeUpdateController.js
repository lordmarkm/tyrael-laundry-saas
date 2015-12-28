define(function () {
  return ['$scope', '$state', 'inventoryItemType', 'brands', 'InventoryItemTypeService', function ($scope, $state, inventoryItemType, brands, InventoryItemTypeService) {
    $scope.inventoryItemType = inventoryItemType;
    $scope.brands = brands;

    if (!inventoryItemType.brandCode && angular.isArray(brands) && brands.length) {
      inventoryItemType.brandCode = brands[0].code;
    }

    $scope.save = function () {
      InventoryItemTypeService.save($scope.inventoryItemType, function (saved) {
        $state.go('default.inv_item_type.view', {invItemTypeCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});