define(function () {
  return ['$scope', 'toaster', 'confirm', 'serviceTypes', 'ServiceTypeService',
    function ($scope, toaster, confirm, serviceTypes, ServiceTypeService) {

    $scope.serviceTypes = serviceTypes;
    $scope.save = function (servicesForm) {
      if (!servicesForm.$valid) {
        toaster.pop('error', 'Invalid price values');
        return;
      }
      confirm.confirm('Save services', 'Updated prices will be applied to all future job orders. Existing job orders will be unaffected')
        .result.then(function (proceed) {
          if (!proceed) {
            return;
          }
          ServiceTypeService.saveList($scope.serviceTypes, function (saved) {
            toaster.pop('success', 'Prices updated');
            $scope.serviceTypes = saved;
            servicesForm.$setPristine(true);
          });
        });
    };

    $scope.reset = function (servicesForm) {
      confirm.confirm('Reset services', 'This will discard all unsaved changes. Proceed?').result.then(function (proceed) {
        if (proceed) {
          ServiceTypeService.get({page: 1}, function (page) {
            $scope.serviceTypes = page.data;
            servicesForm.$setPristine(true);
          });
        }
      });
    };

  }];
});