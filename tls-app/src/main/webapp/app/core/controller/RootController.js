define(function () {
  return ['$scope', '$rootScope', '$state', '$q', 'AuthenticationService', 'ShoppingCartService',
          function ($scope, $rootScope, $state, $q, AuthenticationService, shoppingCart) {

    $scope.contextPath = 'laundry';
    $scope.shoppingCart = shoppingCart;
    $scope.isAuthorized = function (permission) {
      //Anybody can access anonymous access states
      if (permission === 'anonymous') {
        return true;
      }
      //Otherwise, unauthenticated users can't access anything
      if (!$scope.principal) {
        return false;
      }
      if (permission === 'authenticated' && $scope.principal) {
        return true;
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
      } else {
        $scope.principal = auth.principal;
        //disable going to login page if already logged in
        if ($state.current.name === 'default.login') {
          $scope.onLogin();
        }
      }
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
        $state.go('default.login', {msg: 'unauthorized'});
      } else if (typeof toState.data != 'undefined' && typeof toState.data.access != 'undefined' && !$scope.isAuthorized(toState.data.access)) {
        //Also check parentState.data.access
        event.preventDefault();
        $state.go('default.login', {msg: 'unauthorized'});
      }
    });

    //This is for loader
    $scope.cgLoader = {};
    $rootScope.$on('loader_show', function () {
      $scope.cgLoader.promise = $q.defer();
    });
    $rootScope.$on('loader_hide', function () {
      delete $scope.cgLoader.promise;
    });

  }];
});