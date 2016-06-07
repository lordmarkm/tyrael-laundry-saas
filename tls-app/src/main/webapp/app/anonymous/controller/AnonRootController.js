define(function () {
  return ['$rootScope', '$scope', '$q',
          function ($rootScope, $scope, $q) {

    //This is for loader
    $scope.cgLoader = {};
    $rootScope.$on('loader_show', function () {
      $scope.cgLoader.promise = $q.defer();
    });
    $rootScope.$on('loader_hide', function () {
      delete $scope.cgLoader.promise;
    });

    //When customer is set on lower scopes
    $scope.$on('setCustomer', function (event, customer) {
      $scope.customerCode = customer.customerCode;
      $scope.customerName = customer.customerName;
    });

  }];
});