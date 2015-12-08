define(function () {
  return ['$resource', function ($resource) {
    var service = $resource('servicetype', {}, {
      queryAll: {
        url: 'servicetype/all',
        method: 'GET',
        isArray: false
      },
      saveList: {
        url: 'servicetype',
        method: 'POST',
        isArray: true
      }
    });
    service.serviceTypes = service.query();
    return service;
  }];
});