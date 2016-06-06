define(function () {
  return ['$scope', '$http', 'moment', 'events', 'dashboard', 'EventService', function ($scope, $http, moment, events, dashboard, EventService) {

    $scope.moment = moment;
    $scope.page = 1;
    $scope.totalEvents = 0;
    $scope.dashboard = dashboard;
    events.$promise.then(function (e) {
      $scope.events = e.data;
      $scope.totalEvents = e.total;
    });
    dashboard.$promise.then(function (d) {
      $scope.hasAccountsDue = d.accountsDue != 0;
    });
    $scope.moreEvents = function () {
      $scope.page = $scope.page + 1;
      EventService.get({page: $scope.page, count: 5, sort: 'dateCreated,DESC'}, function (page) {
        $scope.events = $scope.events.concat(page.data);
        $scope.totalEvents = page.total;
      });
    };

    $http.get('https://api.github.com/repos/lordmarkm/tyrael-laundry-saas/commits').then(function (commits) {
      $scope.commits = commits.data;
    });
  }];
});