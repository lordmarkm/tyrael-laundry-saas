define(function () {
  return ['$scope', function ($scope) {

    $scope.isSelf = function (name) {
      if (!$scope.principal) {
        return false;
      } else {
        return name === $scope.principal.username;
      }
    };

  }];
});