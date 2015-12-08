define(function () {
  return {
    brand: ['BrandService', '$stateParams', function (BrandService, $stateParams) {
      //Blank brandCode means it's an add operation. Non blank means update
      if ($stateParams.brandCode) {
        return BrandService.get({brandCode: $stateParams.brandCode});
      } else {
        return {};
      }
    }]
  };
});
