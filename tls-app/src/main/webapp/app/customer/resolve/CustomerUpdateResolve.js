define(function () {
  return {
    customer: ['CustomerService', '$stateParams', function (CustomerService, $stateParams) {
      if ($stateParams.customerCode) {
        return CustomerService.get({customerCode: $stateParams.customerCode});
      } else {
        return {};
      }
    }]
  };
});
