define(function () {
  return ['$scope', 'JobOrderStatusService', 'JobOrderService', 'confirm', 'toaster', function ($scope, JobOrderStatusService, JobOrderService, confirm, toaster) {

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
    
    $scope.deleteJobOrder = function (jobOrder, callback) {
      confirm.confirm('Confirm delete Job Order', 'Are you sure you want to delete this job order for ' + jobOrder.customer.formattedName + '? This operation can\'t be undone.')
      .result.then(function (ok) {
        if (ok) {
          JobOrderService.remove({id: jobOrder.id}, function () {
            toaster.pop('success', 'Delete successful', 'Job order for ' + jobOrder.customer.formattedName + ' has been deleted.');
            if (angular.isFunction(callback)) {
              callback();
            }
          });
        }
      });
    };

  }];
});