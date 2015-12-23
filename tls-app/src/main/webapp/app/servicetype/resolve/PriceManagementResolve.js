define(function () {
  return {
    serviceTypes: ['ServiceTypeService', function (ServiceTypeService) {
      return ServiceTypeService.queryAll().$promise;
    }]
  };
});
