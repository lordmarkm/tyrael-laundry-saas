define(function () {
  return ['$resource', function ($resource) {
    return $resource('/user', {}, {
      create: {
        url: '/user/create',
        method: 'post'
      }
    });
  }];
});