define(function () {
  return ['$scope', '$state', 'user', 'brands', 'UserService', function ($scope, $state, user, brands, UserService) {
      $scope.user = user;
      $scope.roles = $scope.isAuthorized('ROLE_ADMIN') ? [
        'ROLE_BRAND_MANAGER',
        'ROLE_POS'
      ] : [
        'ROLE_POS'
      ];
      $scope.brands = brands;
      console.debug(brands);

      $scope.save = function () {
      UserService.save($scope.user, function (saved) {
        $state.go('default.user.view', {userCode: saved.code, urlSlug: saved.slug});
      });
    };
  }];
});