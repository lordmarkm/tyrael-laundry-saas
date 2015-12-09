define(function () {
  return {
    brand: ['BranchService', '$stateParams', function (BranchService, $stateParams) {
      return BrandService.get({brandCode: $stateParams.brandCode, branchCode: $stateParams.branchCode});
    }]
  };
});
