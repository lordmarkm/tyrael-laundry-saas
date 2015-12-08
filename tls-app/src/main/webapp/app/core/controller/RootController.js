define(function () {
  return ['$scope', '$rootScope', '$state', 'auth', function ($scope, $rootScope, $state, auth) {
    $scope.contextPath = 'laundry';
    $scope.isAuthorized = function (permission) {
      if (!$scope.principal) {
        return false;
      }
      for (var i in $scope.principal.authorities) {
        if ($scope.principal.authorities[i].authority === permission) {
          return true;
        }
      }
      return false;
    };

    //Check user authorities and redirect where appropriate
    auth.then(function(authentication) {
      if (!authentication.principal) {
        $state.go('default.login');
      }
      $scope.principal = authentication.principal;
    });

    $rootScope.$on('$stateChangeStart', function (event, toState) {
      if (typeof toState.access != 'undefined' && !$scope.isAuthorized(toState.access)) {
        event.preventDefault();
        $state.go('default.login', {msg: "unauthorized"});
      }
    });
  }];
});