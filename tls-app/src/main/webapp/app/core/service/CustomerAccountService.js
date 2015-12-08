define(function () {
  return ['$resource', function ($resource) {
    return $resource('customeraccount', null, {
      getCurrent: {
        method: 'GET',
        url: 'customeraccount/current',
        isArray: false
      }
    });
  }];
});