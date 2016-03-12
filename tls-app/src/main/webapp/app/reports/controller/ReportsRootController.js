define(function () {
  return ['$scope',
          function ($scope) {

    $scope.saikuRepositoryService = $resource('/saiku/rest/saiku/admin/repository');

    $scope.saikuFile = 'wait.saiku';

    $scope.saikuClient.execute({
      file: $scope.saikuFile,
      htmlObject: "#saiku-chart",
      render: "chart",
      mode: "bar",
      zoom: true,
      chartDefinition: {
        height: 400,
        colors: ["red", "blue", "green", "yellow"],
        baseAxisSize: "300px",
        baseAxisSizeMax : "200"
      }
    });

    $scope.saikuClient.execute({
      file: $scope.saikuFile,
      htmlObject: "#saiku-table",
      render: "table"
    });

  }];
});