define(function () {
  return {
    brands: ['$q', 'BrandService', function ($q, BrandService) {
      var brands = $q.defer();
      BrandService.get({page: 1, count: 9999}, function(response) {
        brands.resolve(response.data);
      });
      return brands.promise;
    }]
  };
});
