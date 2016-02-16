define(function () {
  return ['$scope', '$state', '$modal', 'toaster', 'ShoppingCartService', 'SalesHeaderService',
          function ($scope, $state, $modal, toaster, shoppingCart, SalesHeaderService) {

    $scope.shoppingCart = shoppingCart;

    function showCheckoutModal() {
      return $modal.open({
        templateUrl: 'inventory/view/modal-confirm-checkout.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', 'ShoppingCartService', function($modalScope, $modalInstance, shoppingCart) {
          $modalScope.totalPrice = shoppingCart.totalPrice();
          $modalScope.proceed = function () {
            $modalInstance.close(true);
          };
          $modalScope.cancel = function () {
            $modalInstance.close(false);
          };
        }]
      });
    }

    $scope.checkout = function () {
      showCheckoutModal().result.then(function (proceed) {
        if (proceed) {
          SalesHeaderService.save($scope.shoppingCart, function (salesHeader) {
            toaster.pop('success', 'Sale complete', 'Sale complete');
            shoppingCart.onCheckout();
            $state.go('default.sales_header.view', {id: salesHeader.id});
          });
        }
      });
    };

  }];
});