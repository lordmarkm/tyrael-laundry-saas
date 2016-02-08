define(function () {
  return {
    inventoryItem: ['InventoryItemService', '$stateParams', function (InventoryItemService, $stateParams) {
      return InventoryItemService.get({invItemCode: $stateParams.invItemCode});
    }]
  };
});
