define(function () {
  return {
    brand: ['BrandService', '$stateParams', function (BrandService, $stateParams) {
      return BrandService.get({brandCode: $stateParams.brandCode});
    }]
  };
});
