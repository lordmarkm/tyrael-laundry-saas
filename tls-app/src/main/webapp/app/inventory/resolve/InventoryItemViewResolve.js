define(function () {
  return {
    inventoryItemType: ['InventoryItemTypeService', '$stateParams', function (InventoryItemTypeService, $stateParams) {
      return InventoryItemTypeService.get({invItemTypeCode: $stateParams.invItemTypeCode});
    }]
  };
});
