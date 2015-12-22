define(function () {
  return ['$scope', '$modal', '$filter', 'confirm', 'toaster', 'serviceTypes', 'jobItemTypes', 'CustomerService', 'ServiceTypeService', 'JobOrderService', 'BranchService',
    function ($scope, $modal, $filter, confirm, toaster, serviceTypes, jobItemTypes, CustomerService, ServiceTypeService, JobOrderService, BranchService) {

    function resetPage() {
      $scope.customerHolder = {};
      ServiceTypeService.query().$promise.then(function (serviceTypes) {
        $scope.serviceTypes = serviceTypes.data;
        $scope.serviceTypeHolder = {
            serviceType: $scope.serviceTypes[0]
        };
        $scope.jobItemTypes = jobItemTypes;
        $scope.jobItems = {};
      });
    }

    //Init job items
    $scope.jobItemTypes = jobItemTypes;
    $scope.jobItems = {};

    //Search for existing customer
    $scope.customerHolder = {};
    $scope.customers = [];
    $scope.refreshCustomers = function(term) {
      var params = {term: term, page: 1, count: 10};
      CustomerService.get(params, function (page) {
        $scope.customers = page.data;
      });
    };

    //Create new customer
    $scope.createNewCustomer = function () {
      CustomerService.createCustomer().then(function (customer) {
        $scope.customerHolder.customer = customer;
      });
    };

    //Initialize/process service types
    serviceTypes.$promise.then(function () {
      $scope.serviceTypes = serviceTypes.data;
      $scope.serviceTypeHolder = {
        serviceType: $scope.serviceTypes[0]
      };
    });
    $scope.setWeight = function (weight) {
      $scope.serviceTypeHolder.serviceType.weight = weight;
      toaster.pop('success', 'Service added', $scope.serviceTypeHolder.serviceType.label + ': ' + weight + ' Kg');
    };

    //Update branch options on customer select
    $scope.onCustomerSelect = function () {
      BranchService.query({brandCode: $scope.customerHolder.customer.brandCode, byBrandCode: true}, function (response) {
        $scope.branches = response;
        if (response.length) {
          $scope.branch = response[0];
        }
      });
    };

    //Validate & Submit job order
    $scope.createJobOrder = function (valid) {
      if (!valid) {
        toaster.pop('error', 'Job Order form is invalid');
        return;
      }
      if (!validateJobOrder()) {
        return;
      }
      if (!$scope.branch) {
        toaster.pop('error', 'Branch required', 'Job orders must be assigned to a branch');
        return false;
      }

      showConfirmJobOrderDialog().result.then(function(jobOrder) {
        if (jobOrder) {
          JobOrderService.save(jobOrder, function (savedJob) {
            toaster.pop('success', 'Job Order saved', 'Job order w/ tracking number ' + savedJob.trackingNo + ' saved.');
            resetPage();
            showSaveCompleteDialog(savedJob);
          }, function () {
            toaster.pop('error', 'Error saving Job order');
          });
        }
      });

      function validateJobOrder() {
        if (!$scope.customerHolder.customer) {
          toaster.pop('error', 'Customer required', 'Please select an existing customer record or create a new one');
          return false;
        }

        var totalWeight = 0;
        for (var i in $scope.serviceTypes) {
          totalWeight += $scope.serviceTypes[i].weight || 0;
        }
        if (!totalWeight) {
          toaster.pop('error', 'Services required', 'Please select at least one service and enter the corresponding weight');
          return false;
        }

        return true;
      }

      function showConfirmJobOrderDialog() {
        return $modal.open({
          templateUrl: 'modal-confirm-joborder',
          background: 'static',
          controller: ['$scope', '$modalInstance', 'jobOrder', function($modalScope, $modalInstance, jobOrder) {

            if (jobOrder.totalAmount < $scope.branch.minimumJobOrderAmount) {
              confirm.confirm('Confirm minimum amount', 'This job order does not exceed the minimum job order amount of ' + $filter('currency')($scope.branch.minimumJobOrderAmount, 'Php ') + '. The minimum amount will be charged.')
                .result.then(function (ok) {
                  if (ok) {
                    jobOrder.totalAmount = $scope.branchInfo.minimumJobOrderAmount;
                  } else {
                    $modalInstance.close(false);
                  }
                });
            }

            $modalScope.jobOrder = jobOrder;
            $modalScope.proceed = function () {
              $modalInstance.close(jobOrder);
            };
            $modalScope.cancel = function () {
              $modalInstance.close(false);
            };
          }],
          resolve: {
            jobOrder: function () {
              var jobOrder = {
                  customer: $scope.customerHolder.customer,
                  jobServices: [],
                  jobItems: [],
                  lostAndFoundItems: [],
                  totalAmount: 0,
                  totalAmountPaid: 0,
                  status: 'NEW',
                  branchInfo: $scope.branch
              };
              for (var i in $scope.serviceTypes) {
                var serviceType = $scope.serviceTypes[i];
                if (!serviceType.weight) {
                  continue;
                }

                var amount = serviceType.weight * serviceType.pricePerKilo;
                jobOrder.totalAmount += amount;
                jobOrder.jobServices.push({
                  serviceType: serviceType,
                  weightInKilos: serviceType.weight,
                  pricePerKilo: serviceType.pricePerKilo,
                  amount: amount
                });
              }
              for (var i in $scope.jobItems) {
                if ($scope.jobItems[i]) {
                  jobOrder.jobItems.push({
                    jobItemType: i,
                    quantity: $scope.jobItems[i]
                  });
                }
              }
              return jobOrder;
            }
          }
        });
      }

      function showSaveCompleteDialog(savedJob) {
        $modal.open({
          templateUrl: 'modal-create-success',
          controller: ['$scope', '$state', '$modalInstance', function($scope, $state, $modalInstance) {
            $scope.jobOrder = savedJob;
            $scope.backToDashboard = function () {
              $modalInstance.close();
              $state.go('default.pos.splash');
            };
            $scope.viewJobOrder = function () {
              $modalInstance.close();
              $state.go('default.pos.joborder_view', {trackingNo: savedJob.trackingNo});
            };
            $scope.createNew = function () {
              $modalInstance.close();
            };
          }]
        });
      }
    };





  }];
});