define(function () {
  return ['$resource', function ($resource) {
    return $resource('/servicetype', {}, {
      saveList: {
        url: '/servicetype/batch',
        method: 'POST',
        isArray: true
      }
    });
  }];
});