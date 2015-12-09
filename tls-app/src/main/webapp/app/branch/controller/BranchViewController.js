define(function () {
  return ['$scope', 'brand', 'branch', function ($scope, brand, branch) {
    $scope.brand = brand;
    $scope.branch = branch;
  }];
});