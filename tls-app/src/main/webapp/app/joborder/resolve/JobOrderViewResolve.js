define(function () {
  return {
    joborder: ['JobOrderService', '$stateParams', function (JobOrderService, $stateParams) {
      return JobOrderService.get({trackingNo: $stateParams.joborderCode});
    }],
    jobItemTypes: ['JobItemTypeService', function (JobItemTypeService) {
      return JobItemTypeService.query();
    }]
  };
});
