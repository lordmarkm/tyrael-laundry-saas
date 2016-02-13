define(function () {
  return ['$scope', '$modal', 'toaster', 'ShoppingCartService', 'SalesHeaderService',
          function ($scope, $modal, toaster, shoppingCart, SalesHeaderService) {

    $scope.shoppingCart = shoppingCart;
    
    $scope.checkout = function () {
      SalesHeaderService.save($scope.shoppingCart, function () {
        toaster.pop('success', 'Sale complete', 'Sale complete');
      });
    };

  }];
});