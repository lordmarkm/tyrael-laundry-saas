define(function () {
  return ['$scope', 'ngTableParams', 'InventoryItemService', function ($ascope, ngTableParams, InventoryItemService) {

    //List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
        params.$params.sort = 'name,ASC';
        if ($scope.namefilter) {
          params.$params.term = $scope.namefilter;
        } else {
          delete params.$params.term;
        }
        InventoryItemService.get(params.$params, function(response) {
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