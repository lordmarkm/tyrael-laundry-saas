define(function () {
  return {
    user: ['UserService', '$stateParams', function (UserService, $stateParams) {
      if ($stateParams.userCode) {
        return UserService.get({userCode: $stateParams.userCode});
      } else {
        return undefined;
      }
    }],
    //The brands that the user may be assigned to
    brands: ['$q', 'BrandService', function ($q, BrandService) {
      var brands = $q.defer();
      BrandService.get({page: 1, count: 9999}, function(response) {
        brands.resolve(response.data);
      });
      return brands.promise;
    }],
    //The brands that the user is already assigned to
    userBrands: ['$q', '$stateParams', 'BrandService', function ($q, $stateParams, BrandService) {
      var brands = $q.defer();
      if (!$stateParams.userCode) {
        brands.resolve([]);
      } else {
        BrandService.query({userCode: $stateParams.userCode}, function (response) {
          brands.resolve(response);
        });
      }
      return brands.promise;
    }]
  };
});
