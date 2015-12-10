define(function () {
  return {
    user: ['UserService', '$stateParams', function (UserService, $stateParams) {
      return UserService.get({userCode: $stateParams.userCode});
    }]
  };
});
