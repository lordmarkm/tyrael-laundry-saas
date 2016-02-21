define(function () {
  return {
    acctspayable: ['AcctsPayableService', '$stateParams', function (AcctsPayableService, $stateParams) {
      if ($stateParams.apCode) {
        return AcctsPayableService.get({apCode: $stateParams.apCode});
      } else {
        return {
          repeat: {
            repeatType: 'NONE'
          }
        };
      }
    }],
    //The branchess that the ap may be assigned to
    branches: ['$q', 'BranchService', function ($q, BranchService) {
      var branches = $q.defer();
      BranchService.get({page: 1, count: 9999}, function(response) {
        branches.resolve(response.data);
      });
      return branches.promise;
    }]
  };
});
