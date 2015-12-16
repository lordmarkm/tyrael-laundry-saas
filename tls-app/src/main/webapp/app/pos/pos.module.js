define([
   'angular',
   'pos/controller/PosRootController'
], function (angular, PosRootController) {
  console.debug('Configuring pos.module');
  angular.module('pos.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.pos', {
        url: 'pos',
        template: '<ui-view></ui-view>',
        controller: PosRootController,
        abstract: true
      }).state('default.pos.dashboard', {
        url: '',
        templateUrl: 'pos/view/dashboard.html',
        access: 'ROLE_POS'
      });
    }]);

});