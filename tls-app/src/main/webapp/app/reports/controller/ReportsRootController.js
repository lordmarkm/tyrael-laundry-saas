define(function () {
  return ['$scope', '$resource', 'moment', 'brands',
          function ($scope, $resource, moment, brands) {

    $scope.params = {};
    $scope.pageTitle = 'Reports Dashboard';
    $scope.saikuRepositoryService = $resource('/saiku/rest/saiku/admin/repository');
    $scope.reports = $scope.saikuRepositoryService.query(function (reports) {

      //Remove the non-parametrized reports
      for (var i = 0; i < reports.length; i++) {
        if (reports[i].xml.indexOf('${') == -1) {
          reports.splice(i, 1);
        }
      }
      if (!$scope.reports.length) {
        return;
      }
      $scope.reports = reports;
      if (reports.length) {
        $scope.params.report = $scope.reports[0];
      }
    });

    $scope.hasParam = function (paramName, report) {
      return report.xml.indexOf('${' + paramName + '}') != -1;
    };

    //Set first brand as default
    $scope.brands = brands;
    if ($scope.brands.length) {
      $scope.params.brand = $scope.brands[0];
    }

    //Compute the available dates
    var now = moment(), monthNow = now.get('month'), yearNow = now.get('year');

    //Available years are 2016 to present year
    $scope.params.year = yearNow;
    $scope.years = [];
    for (var i = 2010; i <= yearNow; i++) {
      $scope.years.push(i);
    }
    $scope.yearUpdated = function (year) {
      if (year === yearNow) {
        $scope.params.month = $scope.months[monthNow];
      }
      computeAvailableMonths();
    }

    //Default month is current month
    $scope.months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    $scope.availableMonths = [];

    function computeAvailableMonths () {
      var clone;
      if ($scope.params.year != yearNow) {
        $scope.availableMonths = $scope.months;
      } else {
        clone = $scope.months.slice(0);
        clone.splice(monthNow + 1, 11 - monthNow);
        $scope.availableMonths = clone;
      }
    };
    computeAvailableMonths();
    $scope.params.month = $scope.months[monthNow];

    $scope.saikuClient = new SaikuClient({
      server: "/saiku",
      path: "/rest/saiku/embed"
    });

    //Re-execute the selected query
    $scope.refreshQueries = function () {
      if (!$scope.params.brand || !$scope.params.month || !$scope.params.year) {
        return;
      }

      $scope.pageTitle = $scope.params.brand.name + ' ' + $scope.params.report.name;

      $scope.saikuClient.execute({
        file: $scope.params.report.name + '.saiku',
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
          brandCode: $scope.params.brand.code,
          year: $scope.params.year,
          month: $scope.params.month
        }
      });

      //clear the table view in case no data is retrieved since saiku api leaves the existing table
      angular.element('#saiku-table').html('');

      $scope.saikuClient.execute({
        file: $scope.params.report.name + '.saiku',
        htmlObject: "#saiku-table",
        render: "table",
        params: {
          brandCode: $scope.params.brand.code,
          year: $scope.params.year,
          month: $scope.params.month
        }
      });
    };




  }];
});