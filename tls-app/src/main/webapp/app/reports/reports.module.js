define([
   'angular',
   'reports/controller/ReportsRootController',
   'reports/resolve/ReportsDashboardResolve'
], function (angular, ReportsRootController, ReportsDashboardResolve) {

  console.debug('Configuring reports.module');
  angular.module('reports.module', [])
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.reports', {
        url: 'reports',
        template: '<ui-view></ui-view>',
        controller: ReportsRootController,
        resolve: ReportsDashboardResolve,
        access: ['ROLE_ADMIN', 'ROLE_BRAND_MANAGER'],
        abstract: true
      })
      .state('default.reports.dashboard', {
        url: '',
        templateUrl: 'reports/view/dashboard.html'
      });
    }]);

});