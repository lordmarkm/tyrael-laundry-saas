define(function () {
  return ['$scope', 'user', 'brands', function ($scope, user, brands) {
    $scope.user = user;
    $scope.brands = brands;
    $scope.joinBrands = function (brands) {
      return brands.map(function (brand) {
        return brand.name;
      }).join(',');
    };
  }];
});