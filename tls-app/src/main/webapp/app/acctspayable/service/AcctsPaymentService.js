define(function () {
  return ['$resource', function ($resource) {
    return $resource('/accounts-payment');
  }];
});