define(function () {
  return {
    brand: ['$stateParams', 'BrandService', function ($stateParams, BrandService) {
      
    }]
    branch: ['BranchService', '$stateParams', function (BranchService, $stateParams) {
      return BrandService.get({brandCode: $stateParams.brandCode, branchCode: $stateParams.branchCode});
    }]
  };
});
