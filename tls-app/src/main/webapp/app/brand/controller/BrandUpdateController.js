define(function () {
  return ['$scope', '$state', 'brand', 'BrandService', function ($scope, $state, brand, BrandService) {
    $scope.brand = brand;
    $scope.save = function () {
      BrandService.save($scope.brand, function (saved) {
        $state.go('default.brand.view', {brandCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});