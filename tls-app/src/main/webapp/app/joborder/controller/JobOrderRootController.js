define(function () {
  return ['$scope', 'JobOrderStatusService', function ($scope, JobOrderStatusService) {

    $scope.colorizeBalance = function (joborder) {
      if (joborder.totalAmount > joborder.totalAmountPaid) {
        return 'text-danger text-strong';
      } else if (joborder.totalAmountPaid > joborder.totalAmount) {
        return 'text-success text-strong';
      } else {
        return '';
      }
    };

    //Job order status code to label
    $scope.josLabel = function (code) {
      return JobOrderStatusService.toLabel(code);
    };
  }];
});