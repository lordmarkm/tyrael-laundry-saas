define(function () {
  return ['$resource', '$modal', '$q', 'toaster', function ($resource, $modal, $q, toaster) {
    var CustomerService = $resource('customer/:id/:username');

    CustomerService.createCustomer = function () {
      var defer = $q.defer();

      showCreateCustomerDialog().result.then(function (customer) {
        if (customer) {
          CustomerService.save(customer, function (savedCustomer) {
            toaster.pop('success', 'Customer created', savedCustomer.formattedName + '\'s customer record has been created.');
            defer.resolve(savedCustomer);
          }, function () {
            toaster.pop('error', 'Error creating customer');
          });
        }
      });

      return defer.promise;

      function showCreateCustomerDialog() {
        return $modal.open({
          templateUrl: 'core/view/modal_create_customer.html',
          controller: ['$scope', '$modalInstance',
            function ($scope, $modalInstance) {
              $scope.customer = {};
              $scope.modalTitle = 'Add new customer';
              $scope.proceed = function () {
                $modalInstance.close($scope.customer);
              };
              $scope.cancel = function () {
                $modalInstance.close(false);
              };
          }]
        });
      }
    };

    return CustomerService;
  }];
});