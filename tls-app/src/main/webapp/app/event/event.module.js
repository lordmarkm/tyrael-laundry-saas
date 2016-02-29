define([
   'angular',
   'event/service/EventService'
], function (angular,
    EventService) {

  console.debug('Configuring event.module');
  angular.module('event.module', [])
    .service('EventService', EventService);

});