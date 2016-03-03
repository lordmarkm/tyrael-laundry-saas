define(function () {
  return ['$scope', 'events', 'dashboard', 'EventService', function ($scope, events, dashboard, EventService) {

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

  }];
});