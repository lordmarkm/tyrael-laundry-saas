define(function () {
  return ['$scope', '$modal', function ($scope, $modal) {

    function showMakePaymentModal(acctspayable) {
      
    }

    $scope.makePayment = function (acctspayable) {
      showMakePaymentModal(acctspayable).result.then(function (payment) {
        
      });
    };

  }];
});