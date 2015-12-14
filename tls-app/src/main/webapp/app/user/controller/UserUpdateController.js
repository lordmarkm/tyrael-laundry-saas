define(function () {
  return ['$scope', '$state', 'toaster', 'user', 'brands', 'userBrands', 'UserService', function ($scope, $state, toaster, user, brands, userBrands, UserService) {

    $scope.userBrands = userBrands;
    $scope.user = user || {
      roles: ['ROLE_POS']
    };
    $scope.updateUserRequest = {
      user: $scope.user,
      brandCodes: []
    };
    $scope.roles = $scope.isAuthorized('ROLE_ADMIN') ? ['ROLE_BRAND_MANAGER', 'ROLE_POS'] : ['ROLE_POS'];
    $scope.brands = brands;
    $scope.brandsAvailable = {};

    //Initially set all available brands to false
    for (var i in brands) {
      //e.g. brandsAvailable['olx12'] = false;
      $scope.brandsAvailable[brands[i].code] = false;
    }
    //Set user brands to true
    for (var i in userBrands) {
      if (!userBrands[i].code) {
        continue;
      }
      $scope.brandsAvailable[userBrands[i].code] = true;
      $scope.updateUserRequest.brandCodes.push(userBrands[i].code);
    }

    //For displaying brand name in the checkbox part
    $scope.brandName = function (brandCode) {
      for (var i in brands) {
        if (brands[i].code === brandCode) {
          return brands[i].name;
        }
      }
    };

    //When the user checks/unchecks a brand code checkbox
    $scope.toggleUserBrandCode = function (brandCode, included) {
      console.debug('brand code ' + brandCode + ' is ' + (included ? 'included' : 'excluded'));
      if (included) {
        //Add to brandcodes
        $scope.updateUserRequest.brandCodes.push(brandCode);
      } else {
        for (var i in $scope.updateUserRequest.brandCodes) {
          if ($scope.updateUserRequest.brandCodes[i] === brandCode) {
            $scope.updateUserRequest.brandCodes.splice(i, 1);
          }
        }
      }
    };

    //When the user resets a password
    $scope.resetPassword = function () {
      $scope.updateUserRequest.resetPassword = true;
      delete $scope.updateUserRequest.user.password;
    };

    $scope.save = function () {
      UserService.create($scope.updateUserRequest, function (savedUser) {
        toaster.pop('success', 'Succesfully updated user', 'User has been successfully updated');
        $state.go('default.user.view', {userCode: savedUser.code, urlSlug: savedUser.slug});
      }, function (e) {
        toaster.pop('error', 'Error updating user', e.data && e.data.message ? e.data.message : 'Unknown error');
      });
    };
  }];
});