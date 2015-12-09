define([
   'angular',
   'branch/controller/BranchRootController',
   'branch/controller/BranchListController',
   'branch/controller/BranchViewController',
   'branch/controller/BranchUpdateController',
   'branch/service/BranchService',
   'branch/resolve/BranchListResolve',
   'branch/resolve/BranchViewResolve',
   'branch/resolve/BranchUpdateResolve'
], function (angular, BranchRootController, BranchListController, BranchViewController, BranchUpdateController,
    BranchService,
    BranchListResolve, BranchViewResolve, BranchUpdateResolve) {

  console.debug('Configuring branch.module');
  angular.module('branch.module', [])
    .service('BranchService', BranchService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.branch', {
        url: '{brandCode}/branches',
        template: '<ui-view></ui-view>',
        controller: BranchRootController,
        abstract: true
      })
      .state('default.branch.list', {
        url: '',
        templateUrl: 'branch/view/list.html',
        controller: BranchListController,
        resolve: BranchListResolve
      })
      .state('default.branch.add', {
        url: '/add',
        templateUrl: 'branch/view/update.html',
        controller: BranchUpdateController,
        resolve: BranchUpdateResolve
      })
      .state('default.branch.view', {
        url: '/{branchCode}/{urlSlug}',
        templateUrl: 'branch/view/view.html',
        controller: BranchViewController,
        resolve: BranchViewResolve
      });
    }]);

});