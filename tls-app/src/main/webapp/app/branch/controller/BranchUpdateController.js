define(function () {
  return ['$scope', '$state', 'brand', 'branch', 'BranchService', function ($scope, $state, brand, branch, BranchService) {
    $scope.brand = brand;
    $scope.branch = branch;
    $scope.save = function () {
      $scope.branch.brand = $scope.brand;
      BranchService.save($scope.branch, function (saved) {
        $state.go('default.branch.view', {brandCode: brand.code, branchCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});