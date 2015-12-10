define(function () {
  return {
    //The brands that the user may be assigned to
    brands: ['$q', 'BrandService', function ($q, BrandService) {
      var brands = $q.defer();
      BrandService.get({page: 1, count: 9999}, function(response) {
        brands.resolve(response.data);
      });
      return brands.promise;
    }],
    user: ['UserService', '$stateParams', function (UserService, $stateParams) {
      //Blank userCode means it's an add operation. Non blank means update
      if ($stateParams.userCode) {
        return UserService.get({userCode: $stateParams.userCode});
      } else {
        return {};
      }
    }]
  };
});
