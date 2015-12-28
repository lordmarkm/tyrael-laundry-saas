define(function () {
  return {
    inventoryItemType: ['InventoryItemTypeService', '$stateParams', function (InventoryItemTypeService, $stateParams) {
      if ($stateParams.invItemTypeCode) {
        return InventoryItemTypeService.get({invItemTypeCode: $stateParams.invItemTypeCode});
      } else {
        return {};
      }
    }],
    //The brands that the inventory item type may be assigned to
    brands: ['$q', 'BrandService', function ($q, BrandService) {
      var brands = $q.defer();
      BrandService.get({page: 1, count: 9999}, function(response) {
        brands.resolve(response.data);
      });
      return brands.promise;
    }]
  };
});
