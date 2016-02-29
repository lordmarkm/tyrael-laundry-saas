define(function () {
  return ['$scope', 'events', 'dashboard', function ($scope, events, dashboard) {

    $scope.events = events;
    $scope.dashboard = dashboard;

  }];
});