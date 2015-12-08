define(function () {
  return {
    branchInfo: ['BranchInfoService', function (BranchInfoService) {
      return BranchInfoService.get();
    }]
  };
});
