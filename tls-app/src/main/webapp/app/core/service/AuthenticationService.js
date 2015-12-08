define(function () {
  return ['$resource', '$state', function ($resource, $state) {
    this.service = $resource('auth');
    return this.service.get().$promise;
  }];
});