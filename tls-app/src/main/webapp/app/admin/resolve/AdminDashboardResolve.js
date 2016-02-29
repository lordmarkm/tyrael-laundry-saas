define(function () {
  return {
    events: ['EventService', function (EventService) {
      return EventService.query();
    }]
  };
});
