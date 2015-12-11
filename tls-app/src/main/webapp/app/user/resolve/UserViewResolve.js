define(function () {
  return {
    user: ['UserService', '$stateParams', function (UserService, $stateParams) {
      return UserService.get({userCode: $stateParams.userCode});
    }],
    brands: ['BrandService', '$stateParams', function (BrandService, $stateParams) {
      return BrandService.query({userCode: $stateParams.userCode});
    }]
  };
});
