define(function () {
  return {
    brand: ['BrandService', '$stateParams', function (BrandService, $stateParams) {
      return BrandService.get({brandCode: $stateParams.brandCode});
    }],
    branch: ['BranchService', '$stateParams', function (BranchService, $stateParams) {
      //Blank branchCode means it's an add operation. Non blank means update
      if ($stateParams.branchCode) {
        return BranchService.get({brandCode: $stateParams.brandCode, branchCode: $stateParams.branchCode});
      } else {
        return {};
      }
    }]
  };
});
