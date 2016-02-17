define(function () {
  return ['$scope', '$modal', '$stateParams', 'ngTableParams', 'branches', 'moment', 'JobOrderService', 'JobOrderStatusService',
          function ($scope, $modal, $stateParams, ngTableParams, branches, moment, JobOrderService, JobOrderStatusService) {

    //Filters
    $scope.filter = {};
    $scope.branches = branches;
    $scope.statuses = JobOrderStatusService.statusList();
    $scope.dateFormat = 'yyyy MMM-dd';
    $scope.today = moment();
    $scope.customerName = $stateParams.customerName;

    $scope.clearFilters = function () {
      $scope.filter = {};
      reloadTable();
    };

    $scope.doFilter = function () {
      reloadTable();
    };

    $scope.open = function (picker, evt) {
      evt.preventDefault();
      evt.stopPropagation();
      
      if (picker === 'from') {
        $scope.openDatefrom = true;
      } else if (picker === 'to') {
        $scope.openDateto = true;
      }
    };

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
        params.$params.term = composeSearchTerm();
        JobOrderService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function reloadTable() {
      if (table.page() == 1) {
        table.reload();
      } else {
        table.page(1);
      }
    }

    function composeSearchTerm() {
      var term = '', 
          customerCode = $stateParams.customerCode || $scope.filter.customerCode;
      if (customerCode) {
        appendTerm('customerCode==' + customerCode);
      } else if ($scope.filter.customerNameLike) {
        appendTerm('(customerSurname==' + $scope.filter.customerNameLike + '*,customerGivenName==' + $scope.filter.customerNameLike + '*)');
      }
      if ($scope.filter.branchCode) {
        appendTerm('branchCode==' + $scope.filter.branchCode);
      }
      if ($scope.filter.status) {
        appendTerm('status==' + $scope.filter.status);
      }
      if ($scope.filter.datefrom) {
        appendTerm('dateReceived>=' + moment($scope.filter.datefrom).format('YYYY-MM-DD'));
      }
      if ($scope.filter.dateto) {
        appendTerm('dateReceived<=' + moment($scope.filter.dateto).format('YYYY-MM-DD'));
      }
      function appendTerm(termToAppend) {
        if (term.length) {
          term += ';';
        }
        term += termToAppend;
      }
      return term;
    }

    //Filter by customer
    $scope.filterByCustomer = function (customer) {
      $scope.showFilters = true;
      $scope.filter.customerCode = customer.code;
      $scope.filter.customerName = customer.formattedName;
      reloadTable();
    };

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