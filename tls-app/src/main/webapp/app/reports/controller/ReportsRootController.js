define(function () {
  return ['$scope', '$resource', 'moment', 'brands',
          function ($scope, $resource, moment, brands) {

    $scope.saikuRepositoryService = $resource('/saiku/rest/saiku/admin/repository');
    $scope.saikuFile = 'fzz.saiku';

    //Set first brand as default
    $scope.brands = brands;
    if ($scope.brands.length) {
      $scope.brand = $scope.brands[0];
    }

    //Compute the available dates
    var now = moment(), monthNow = now.get('month'), yearNow = now.get('year');

    //Available years are 2016 to present year
    $scope.years = [];
    for (var i = 2016; i <= 2018; i++) {
      $scope.years.push(i);
    }
    $scope.year = $scope.years[$scope.years.length - 1];

    //Default month is current month
    $scope.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    $scope.availableMonths = function () {
      console.debug('compute months. $scope.year=' + $scope.year + ',yearnow=' + yearNow);
      var clone;
      if ($scope.year != yearNow) {
        return $scope.months;
      } else {
        clone = $scope.months.slice(0);
        clone.splice(monthNow + 1, 11 - monthNow);
        return clone;
      }
    };
    $scope.month = $scope.months[monthNow];

    $scope.saikuClient = new SaikuClient({
      server: "/saiku",
      path: "/rest/saiku/embed"
    });

    //Re-execute the selected query
    $scope.refreshQueries = function () {
      return;
      if (!$scope.brand.code) {
        return;
      }

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
          brandCode: $scope.brand.code
        }
      });
    };

    $scope.refreshQueries();




  }];
});