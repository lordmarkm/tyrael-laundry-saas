define(function () {
  return ['$scope', '$modal', 'ngTableParams', 'JobOrderService', 'JobOrderStatusService',
          function ($scope, $modal, ngTableParams, JobOrderService, JobOrderStatusService) {

    //List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
        params.$params.sort = "dateCreated,DESC";
        JobOrderService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function reloadTable() {
      table.reload();
    }

    //Handle payments and change
    function showPaymentDialog(joborder) {
      return $modal.open({
        templateUrl: 'joborder/view/modal-make-payment.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', function ($modalScope, $modalInstance) {
          $modalScope.joborder = joborder;
          $modalScope.paymentAmount = joborder.totalAmount - joborder.totalAmountPaid;
          $modalScope.proceed = function () {
            $modalInstance.close($modalScope.paymentAmount);
          };
          $modalScope.cancel = function () {
            $modalInstance.close(false);
          };
        }],
        resolve: {
        }
      });
    };

    function showGiveChangeDialog(joborder, totalAmountPaid) {
      return $modal.open({
        templateUrl: 'joborder/view/modal-give-change.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', function ($modalScope, $modalInstance) {
          $modalScope.joborder = joborder;
          $modalScope.changeAmount = +parseFloat(totalAmountPaid - joborder.totalAmount).toFixed(2);
          $modalScope.maxChange = totalAmountPaid - joborder.totalAmount;
          $modalScope.totalAmountPaid = totalAmountPaid;
          $modalScope.proceed = function () {
            $modalInstance.close($modalScope.changeAmount);
          };
          $modalScope.cancel = function () {
            $modalInstance.close(undefined);
          };
        }],
        resolve: {
        }
      });
    };

    $scope.makePayment = function (joborder) {
      showPaymentDialog(joborder).result.then(function (paymentAmount) {
        if (paymentAmount) {
          var newTotalAmountPaid = joborder.totalAmountPaid + paymentAmount;
          if (newTotalAmountPaid > joborder.totalAmount) {
            //If paid amount now exceeds original balance, offer to give change
            showGiveChangeDialog(joborder, newTotalAmountPaid).result.then(function (changeAmount) {
              if (changeAmount != undefined) {
                joborder.totalAmountPaid = newTotalAmountPaid - changeAmount;
                JobOrderService.save(joborder, function () {
                  reloadTable();
                });
              }
            });
          } else {
            joborder.totalAmountPaid = newTotalAmountPaid;
            JobOrderService.save(joborder, function () {
              reloadTable();
            });
          }
        }
      });
    };

    $scope.giveChange = function (joborder) {
      showGiveChangeDialog(joborder, joborder.totalAmountPaid).result.then(function (changeAmount) {
        if (changeAmount) {
          joborder.totalAmountPaid = joborder.totalAmountPaid - changeAmount;
          JobOrderService.save(joborder, function () {
            reloadTable();
          });
        }
      });
    };

    //Update status
    function showUpdateStatusDialog(joborder) {
      return $modal.open({
        templateUrl: 'joborder/view/modal-update-status.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', function ($modalScope, $modalInstance) {
          $modalScope.joborder = joborder;
          $modalScope.statuses = JobOrderStatusService.statusMap();
          $modalScope.newStatus = joborder.status;
          $modalScope.josLabel = function (code) {
            return JobOrderStatusService.toLabel(code);
          };
          $modalScope.proceed = function () {
            $modalInstance.close($modalScope.newStatus);
          };
          $modalScope.cancel = function () {
            $modalInstance.close(undefined);
          };
        }],
        resolve: {
        }
      });
    }

    $scope.updateStatus = function (joborder) {
      showUpdateStatusDialog(joborder).result.then(function (newStatus) {
        if (newStatus && joborder.status != newStatus) {
          joborder.status = newStatus;
          JobOrderService.save(joborder, function () {
            reloadTable();
          });
        }
      });
    };

  }];
});