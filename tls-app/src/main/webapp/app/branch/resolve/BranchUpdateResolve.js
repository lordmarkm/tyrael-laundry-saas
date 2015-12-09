define(function () {
  return {
    brand: ['BrandService', '$stateParams', function (BrandService, $stateParams) {
      return BrandService.get({brandCode: $stateParams.brandCode});
    }],
    branch: ['BranchService', '$stateParams', function (BranchService, $stateParams) {
      return BranchService.get({brandCode: $stateParams.brandCode, branchCode: $stateParams.branchCode});
    }]
  };
});
