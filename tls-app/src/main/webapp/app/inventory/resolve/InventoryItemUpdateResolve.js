define(function () {
  return {
    inventoryItem: ['InventoryItemService', '$stateParams', function (InventoryItemService, $stateParams) {
      if ($stateParams.invItemCode) {
        return InventoryItemService.get({invItemCode: $stateParams.invItemCode});
      } else {
        return {};
      }
    }],
    //The branchess that the inventory item type may be assigned to
    branches: ['$q', 'BranchService', function ($q, BranchService) {
      var branches = $q.defer();
      BranchService.get({page: 1, count: 9999}, function(response) {
        branches.resolve(response.data);
      });
      return branches.promise;
    }]
  };
});
