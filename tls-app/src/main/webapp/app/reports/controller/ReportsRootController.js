define(function () {
  return ['$scope', '$resource',
          function ($scope, $resource) {

    $scope.saikuRepositoryService = $resource('/saiku/rest/saiku/admin/repository');

    $scope.saikuFile = 'fzz.saiku';

    $scope.saikuClient = new SaikuClient({
      server: "/saiku",
      path: "/rest/saiku/embed"
    });

    $scope.saikuClient.execute({
      file: $scope.saikuFile,
      htmlObject: "#saiku-chart",
      render: "chart",
      mode: "bar",
      zoom: true,
      chartDefinition: {
        height: 400,
        colors: ["blue", "red", "green", "yellow"],
        baseAxisSize: "300px",
        baseAxisSizeMax : "200"
      },
      params: {
        branchCode: 'lnav9'
      }
    });

    $scope.saikuClient.execute({
      file: $scope.saikuFile,
      htmlObject: "#saiku-table",
      render: "table",
      params: {
        branchCode: 'lnav9'
      }
    });

  }];
});