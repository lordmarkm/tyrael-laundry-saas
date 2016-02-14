define(function () {
  return {
    branches: ['$q', 'BranchService', function ($q, BranchService) {
      var branches = $q.defer();
      BranchService.get({page: 1, count: 9999}, function(response) {
        branches.resolve(response.data);
      });
      return branches.promise;
    }]
  };
});
