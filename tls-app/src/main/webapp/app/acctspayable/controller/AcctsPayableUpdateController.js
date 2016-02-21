define(function () {
  return ['$scope', '$state', 'acctspayable', 'branches', 'AcctsPayableService', function ($scope, $state, acctspayable, branches, AcctsPayableService) {
    $scope.acctspayable = acctspayable;
    $scope.branches = branches;

    if (!acctspayable.branchCode && angular.isArray(branches) && branches.length) {
      acctspayable.branchCode = branches[0].code;
    };

    $scope.save = function () {
      AcctsPayableService.save($scope.acctspayable, function (saved) {
        $state.go('default.acctspayable.view', {apCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});