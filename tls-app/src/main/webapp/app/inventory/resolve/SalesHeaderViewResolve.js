define(function () {
  return {
    salesHeader: ['SalesHeaderService', '$stateParams', function (SalesHeaderService, $stateParams) {
      return SalesHeaderService.get({id: $stateParams.id});
    }]
  };
});
