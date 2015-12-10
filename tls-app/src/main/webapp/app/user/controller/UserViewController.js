define(function () {
  return ['$scope', 'user', function ($scope, user) {
    $scope.user = user;
  }];
});