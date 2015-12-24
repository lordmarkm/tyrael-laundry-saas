define(function () {
  return {
    serviceTypes: ['$q', 'ServiceTypeService', function ($q, ServiceTypeService) {
      var servicetypes = $q.defer();
      ServiceTypeService.get({page: 1}, function (results) {
        servicetypes.resolve(results.data);
      });
      return servicetypes.promise;
    }]
  };
});
