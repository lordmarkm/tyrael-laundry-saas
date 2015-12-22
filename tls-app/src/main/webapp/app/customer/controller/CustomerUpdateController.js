define(function () {
  return ['$scope', '$state', 'customer', 'brands', 'CustomerService', function ($scope, $state, customer, brands, CustomerService) {
    $scope.customer = customer;
    $scope.brands = brands;

    if (!customer.brandCode && angular.isArray(brands) && brands.length) {
      customer.brandCode = brands[0].code;
    };

    $scope.save = function () {
      CustomerService.save($scope.customer, function (saved) {
        $state.go('default.customer.view', {customerCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});