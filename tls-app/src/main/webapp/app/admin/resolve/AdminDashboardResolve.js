define(function () {
  return {
    events: ['EventService', function (EventService) {
      return EventService.get({page:1, count: 5, sort: 'dateCreated,DESC'});
    }],
    dashboard: ['DashboardService', function (DashboardService) {
      return DashboardService.get();
    }]
  };
});
