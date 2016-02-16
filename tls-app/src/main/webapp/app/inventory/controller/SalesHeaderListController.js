define(function () {
  return ['$scope', '$state', '$modal', 'ngTableParams', 'toaster', 'SalesHeaderService',
          function ($scope, $state, $modal, ngTableParams, toaster, SalesHeaderService) {

    //List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
        params.$params.sort = 'dateUpdated,DESC';
        if ($scope.namefilter) {
          params.$params.term = $scope.namefilter;
        } else {
          delete params.$params.term;
        }
        SalesHeaderService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    $scope.doFilter = function () {
      if (table.page() === 1) {
        table.reload();
      } else {
        table.page(1);
      }
    };

    $scope.clearFilter = function () {
      delete $scope.namefilter;
      $scope.doFilter();
    };

  }];
});