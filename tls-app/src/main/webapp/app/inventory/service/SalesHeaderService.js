define(function () {
  return ['$resource', function ($resource) {
    return $resource('/sales-header/:id');
  }];
});