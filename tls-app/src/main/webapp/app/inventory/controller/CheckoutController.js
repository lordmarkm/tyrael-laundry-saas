define(function () {
  return ['$scope', '$state', '$modal', 'toaster', 'ShoppingCartService', 'SalesHeaderService',
          function ($scope, $state, $modal, toaster, shoppingCart, SalesHeaderService) {

    $scope.shoppingCart = shoppingCart;
    
    $scope.checkout = function () {
      SalesHeaderService.save($scope.shoppingCart, function (salesHeader) {
        toaster.pop('success', 'Sale complete', 'Sale complete');
        shoppingCart.onCheckout();
        $state.go('default.sales_header.view', {id: salesHeader.id});
      });
    };

  }];
});