define(function () {
  return ['$scope', '$q', '$modal', '$filter', 'confirm', 'toaster', 'joborder', 'serviceTypes', 'jobItemTypes', 'CustomerService', 'ServiceTypeService', 'JobOrderService', 'BranchService',
    function ($scope, $q, $modal, $filter, confirm, toaster, joborder, serviceTypes, jobItemTypes, CustomerService, ServiceTypeService, JobOrderService, BranchService) {

    function resetPage() {
      delete $scope.joborder;
      $scope.customerHolder = {};
      ServiceTypeService.get().$promise.then(function (serviceTypes) {
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
    var serviceTypesPromise = $q.defer();
    serviceTypes.$promise.then(function () {
      $scope.serviceTypes = serviceTypes.data;
      $scope.serviceTypeHolder = {
        serviceType: $scope.serviceTypes[0]
      };
      serviceTypesPromise.resolve($scope.serviceTypes);
    });
    $scope.setWeight = function (weight) {
      $scope.serviceTypeHolder.serviceType.weight = weight;
      toaster.pop('success', 'Service added', $scope.serviceTypeHolder.serviceType.label + ': ' + weight + ' Kg');
    };

    //Update branch options on customer select
    $scope.branchHolder = {};
    $scope.onCustomerSelect = function () {
      BranchService.query({brandCode: $scope.customerHolder.customer.brandCode, byBrandCode: true}, function (response) {
        $scope.branches = response;
        if (response.length) {
          $scope.branchHolder.branch = response[0];
        }
      });
    };

    //Initialize values if update operation
    $scope.joborder = joborder;
    if (joborder) {
      joborder.$promise.then(function (jo) {
        //Initialize customer and location
        $scope.customerHolder.customer = jo.customer;
        BranchService.query({brandCode: $scope.customerHolder.customer.brandCode, byBrandCode: true}, function (response) {
          $scope.branches = response;
          $scope.branchHolder.branch = jo.branchInfo;
        });

        //Initialize services
        serviceTypesPromise.promise.then(function (serviceTypes) {
          for (var i in joborder.jobServices) {
            for (var j in serviceTypes) {
              if (serviceTypes[j].code === joborder.jobServices[i].serviceType.code) {
                console.debug('setting wt');
                serviceTypes[j].weight = joborder.jobServices[i].weightInKilos;
                continue;
              }
            }
          }
        });

        //Initialize job items
        for (var i in joborder.jobItems) {
          $scope.jobItems[joborder.jobItems[i].jobItemType] = joborder.jobItems[i].quantity;
        }
      });
    }

    //Validate & Submit job order
    $scope.createJobOrder = function (valid) {
      if (!valid) {
        toaster.pop('error', 'Job Order form is invalid');
        return;
      }
      if (!validateJobOrder()) {
        return;
      }
      if (!$scope.branchHolder.branch) {
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

            if (jobOrder.totalAmount < $scope.branchHolder.branch.minimumJobOrderAmount) {
              confirm.confirm('Confirm minimum amount', 'This job order does not exceed the minimum job order amount of ' + $filter('currency')($scope.branchHolder.branch.minimumJobOrderAmount, 'Php ') + '. The minimum amount will be charged.')
                .result.then(function (ok) {
                  if (ok) {
                    jobOrder.totalAmount = $scope.branchHolder.branch.minimumJobOrderAmount;
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
              console.debug('$scope.branch=' + $scope.branchHolder.branch.name);
              var jobOrder = {
                  id: joborder ? joborder.id : null,
                  dateReceived: joborder ? joborder.dateReceived : null,
                  dateCompleted: joborder ? joborder.dateCompleted : null,
                  dateClaimed: joborder ? joborder.dateClaimed : null,
                  customer: $scope.customerHolder.customer,
                  jobServices: [],
                  jobItems: [],
                  totalAmount: 0,
                  totalAmountPaid: joborder ? joborder.totalAmountPaid : 0,
                  status: joborder ? joborder.status : 'NEW',
                  branchInfo: $scope.branchHolder.branch
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
            $scope.backToList = function () {
              $modalInstance.close();
              $state.go('default.joborder.list');
            };
            $scope.viewJobOrder = function () {
              $modalInstance.close();
              $state.go('default.joborder.view', {joborderCode: savedJob.trackingNo, urlSlug: savedJob.slug});
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