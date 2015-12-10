define(function () {
  return ['$scope', '$state', 'brands', 'UserService', function ($scope, $state, brands, UserService) {

    $scope.user = {
      roles: ['ROLE_POS']
    };
    $scope.updateUserRequest = {
        user: $scope.user
    };
    $scope.roles = $scope.isAuthorized('ROLE_ADMIN') ? ['ROLE_BRAND_MANAGER', 'ROLE_POS'] : ['ROLE_POS'];
    $scope.brands = brands;

    $scope.save = function () {
      UserService.create($scope.updateUserRequest, function (savedUser) {
        $state.go('default.user.view', {userCode: savedUser.code, urlSlug: savedUser.slug});
      });
    };
  }];
});