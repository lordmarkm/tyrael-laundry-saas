define(function () {
  return {
    customer: ['CustomerService', '$stateParams', function (CustomerService, $stateParams) {
      if ($stateParams.customerCode) {
        return CustomerService.get({customerCode: $stateParams.customerCode});
      } else {
        return {};
      }
    }],
    //The brands that the customer may be assigned to
    brands: ['$q', 'BrandService', function ($q, BrandService) {
      var brands = $q.defer();
      BrandService.get({page: 1, count: 9999}, function(response) {
        brands.resolve(response.data);
      });
      return brands.promise;
    }]
  };
});
