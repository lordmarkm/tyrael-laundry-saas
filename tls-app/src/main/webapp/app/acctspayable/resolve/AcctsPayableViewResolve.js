define(function () {
  return {
    acctspayable: ['AcctsPayableService', '$stateParams', function (AcctsPayableService, $stateParams) {
      return AcctsPayableService.get({apCode: $stateParams.apCode});
    }]
  };
});
