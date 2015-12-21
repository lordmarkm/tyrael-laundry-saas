define(function () {
  return {
    joborder: ['JobOrderService', '$stateParams', function (JobOrderService, $stateParams) {
      return JobOrderService.get({joborderCode: $stateParams.joborderCode});
    }],
    serviceTypes: ['ServiceTypeService', function (ServiceTypeService) {
      return ServiceTypeService.query().$promise;
    }],
    jobItemTypes: ['JobItemTypeService', function (JobItemTypeService) {
      return JobItemTypeService.query();
    }]
  };
});
