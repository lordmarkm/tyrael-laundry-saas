define(function () {
  return ['$scope', '$state', 'user', 'brands', 'UserService', function ($scope, $state, user, brands, userBrands, UserService) {

    $scope.user = user || {
      roles: ['ROLE_POS']
    };
    $scope.updateUserRequest = {
        user: $scope.user,
        brands: []
    };
    $scope.roles = $scope.isAuthorized('ROLE_ADMIN') ? ['ROLE_BRAND_MANAGER', 'ROLE_POS'] : ['ROLE_POS'];
    $scope.brands = brands;

    //Set brands
    console.debug('mother fucks' + userBrands);

    $scope.save = function () {
      UserService.create($scope.updateUserRequest, function (savedUser) {
        $state.go('default.user.view', {userCode: savedUser.code, urlSlug: savedUser.slug});
      });
    };
  }];
});