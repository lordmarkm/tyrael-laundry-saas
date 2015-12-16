define(function () {
  return ['$scope', '$rootScope', '$state', 'AuthenticationService', function ($scope, $rootScope, $state, AuthenticationService) {
    $scope.contextPath = 'laundry';
    $scope.isAuthorized = function (permission) {
      if (!$scope.principal) {
        return false;
      }
      for (var i in $scope.principal.authorities) {
        if (angular.isArray(permission)) {
          //Checking for array permission set for states with access: ['ROLE_ADMIN', 'ROLE_BRANCH_MANAGER']
          for (var j in permission) {
            if ($scope.principal.authorities[i].authority === permission[j]) {
              return true;
            }
          }
        } else {
          //Checking for string permission such as isAuthorized('ROLE_ADMIN')
          if ($scope.principal.authorities[i].authority === permission) {
            return true;
          }
        }
      }
      return false;
    };
    $scope.isAuthenticated = function () {
      return null != $scope.principal;
    }

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
          return;
        }
        $scope.principal = auth.principal;
        if ($scope.isAuthorized('ROLE_ADMIN')) {
          $state.go('default.admin.dashboard');
          return;
        }
        if ($scope.isAuthorized('ROLE_BRAND_MANAGER')) {
          $state.go('default.brandmgr.dashboard');
          return;
        }
        if ($scope.isAuthorized('ROLE_POS')) {
          $state.go('default.pos.dashboard');
          return;
        }
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