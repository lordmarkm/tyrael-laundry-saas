define(function () {
  return ['$scope', 'confirm', 'toaster', 'CustomerService', function ($scope, confirm, toaster, CustomerService) {

    //colorize balance
    $scope.colorizeBalance = function (customer) {
      if (customer.balance > 0) {
        return 'text-danger text-strong';
      } else if (joborder.balance < 0) {
        return 'text-success text-strong';
      } else {
        return '';
      }
    };

    //delete customer
    $scope.deleteCustomer = function (customer, callback) {
      confirm.confirm('Confirm delete customer', 'Are you sure you want to delete ' + customer.formattedName + '? Existing job orders will be unaffected. This operation can\'t be undone.')
      .result.then(function (ok) {
        if (ok) {
          CustomerService.remove({id: customer.id}, function () {
            toaster.pop('success', 'Delete successful', customer.formattedName + ' has been deleted.');
            if (angular.isFunction(callback)) {
              callback();
            }
          });
        }
      });
    };

  }];
});