define(function () {
  return ['$scope', function ($scope) {

    $scope.isSelf = function (name) {
      if (!$scope.principal) {
        return false;
      } else {
        return name === $scope.principal.username;
      }
    };

    //Assume app only allows 1 role for now
    $scope.isSameLevel = function (roles) {
      if (!$scope.principal) {
        return false;
      } else {
        return $scope.principal.authorities[0].authority === roles[0];
      }
    };
  }];
});