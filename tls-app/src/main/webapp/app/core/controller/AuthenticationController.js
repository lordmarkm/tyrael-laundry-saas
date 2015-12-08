define(function () {
  return ['$scope', '$stateParams', 'LoginService', function ($scope, $stateParams, LoginService) {
    $scope.params = $stateParams;
    $scope.authenticate = function () {
      LoginService.save({username: $scope.username, password: $scope.password}, function(resp) {
        console.debug(resp);
      }, function (errorResp) {
        $scope.error = errorResp.data.error || 'Authentication failed';
      })
    };
  }];
});