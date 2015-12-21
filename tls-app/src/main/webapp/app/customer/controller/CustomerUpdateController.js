define(function () {
  return ['$scope', '$state', 'customer', 'CustomerService', function ($scope, $state, customer, CustomerService) {
    $scope.customer = customer;
    $scope.save = function () {
      CustomerService.save($scope.customer, function (saved) {
        $state.go('default.customer.view', {customerCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});