define(function () {
  return ['$scope', '$rootScope', '$state', 'AuthenticationService', function ($scope, $rootScope, $state, AuthenticationService) {
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
    AuthenticationService.get(function (auth) {
      if (!auth.principal) {
        $state.go('default.login');
      }
      $scope.principal = auth.principal;
    });

    $scope.onLogin = function () {
      $scope.principal = AuthenticationService.get(function (auth) {
        if (!auth.principal) {
          $state.go('default.login');
        }
        if ($scope.isAuthorized('ROLE_ADMIN')) {
          $state.go('default.admin.dashboard');
        }
        $scope.principal = auth.principal;
      });
    };

    $rootScope.$on('$stateChangeStart', function (event, toState) {
      if (typeof toState.access != 'undefined' && !$scope.isAuthorized(toState.access)) {
        event.preventDefault();
        $state.go('default.login', {msg: "unauthorized"});
      }
    });
  }];
});