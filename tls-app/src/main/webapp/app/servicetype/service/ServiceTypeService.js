define(function () {
  return ['$resource', function ($resource) {
    return $resource('/servicetype', {}, {
      findEnabled: {
        url: '/servicetype/enabled',
        method: 'GET'
      },
      saveList: {
        url: '/servicetype/batch',
        method: 'POST',
        isArray: true
      }
    });
  }];
});