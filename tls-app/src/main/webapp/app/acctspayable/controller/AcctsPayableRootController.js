define(function () {
  return ['$scope', '$state', '$modal', 'moment', 'toaster', 'AcctsPaymentService',
          function ($scope, $state, $modal, moment, toaster, AcctsPaymentService) {

    function showMakePaymentModal(acctspayable) {
      return $modal.open({
        templateUrl: 'acctspayable/view/modal-make-payment.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', function ($modalScope, $modalInstance) {
          $modalScope.acctspayable = acctspayable;
          $modalScope.dateFormat = 'yyyy MMM-dd';
          $modalScope.today = moment();
          $modalScope.open = function (evt) {
            evt.preventDefault();
            evt.stopPropagation();
            $modalScope.openPaymentDate = true;
          };
          $modalScope.proceed = function () {
            $modalInstance.close({
              amount: $modalScope.paymentAmount,
              paymentDate: $modalScope.paymentDate
            });
          };
          $modalScope.cancel = function () {
            $modalInstance.close(false);
          };
        }],
        resolve: {
        }
      });
    }

    $scope.makePayment = function (acctspayable, callback) {
      showMakePaymentModal(acctspayable).result.then(function (payment) {
        if (!payment) {
          return false;
        }
        AcctsPaymentService.save({
          amount: payment.amount,
          paymentDate: payment.paymentDate,
          accountsPayableCode: acctspayable.code
        }, function (payment) {
          toaster.pop('success', 'Payment success', 'Accounts payable payment successfully recorded.');
          if (angular.isFunction(callback)) {
            callback();
          } else {
            $state.go('default.acctspayable.view', {apCode: acctspayable.code, urlSlug: acctspayable.slug});
          }
        });
      });
    };



  }];
});