define([
   'angular',
   'user/controller/UserRootController',
   'user/controller/UserListController',
   'user/controller/UserViewController',
   'user/controller/UserUpdateController',
   'user/service/UserService',
   'user/resolve/UserViewResolve',
   'user/resolve/UserUpdateResolve'
], function (angular, UserRootController, UserListController, UserViewController, UserUpdateController,
    UserService,
    UserViewResolve, UserUpdateResolve) {

  console.debug('Configuring user.module');
  angular.module('user.module', [])
    .service('UserService', UserService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.user', {
        url: 'user',
        template: '<ui-view></ui-view>',
        controller: UserRootController,
        abstract: true
      })
      .state('default.user.list', {
        url: '',
        templateUrl: 'user/view/list.html',
        controller: UserListController
      })
      .state('default.user.add', {
        url: '/add',
        templateUrl: 'user/view/update.html',
        controller: UserUpdateController,
        resolve: UserUpdateResolve,
        access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER']
      })
      .state('default.user.update', {
        url: '/update/{userCode}/{urlSlug}',
        templateUrl: 'user/view/update.html',
        controller: UserUpdateController,
        resolve: UserUpdateResolve,
        access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER']
      })
      .state('default.user.view', {
        url: '/{userCode}/{urlSlug}',
        templateUrl: 'user/view/view.html',
        controller: UserViewController,
        resolve: UserViewResolve
      });
    }]);

});