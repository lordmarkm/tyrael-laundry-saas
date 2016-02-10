define(function () {
  return ['$scope', '$modal', 'toaster', 'ShoppingCartService',
          function ($scope, $modal, toaster, shoppingCart) {

    $scope.shoppingCart = shoppingCart;

  }];
});