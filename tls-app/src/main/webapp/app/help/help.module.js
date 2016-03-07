define([
   'angular',
   'core/controller/RootController.js'
], function (angular, RootController) {
  console.debug('Configuring help.module');
  angular.module('help.module', [])
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('help', {
        url: '/help/',
        templateUrl: 'help/view/help.html',
        controller: RootController,
        abstract: true
      })
      .state('help.index', {
        url: 'index',
        templateUrl: 'help/view/index.html'
      });

    }]);
});