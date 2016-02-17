define(function () {
  return ['$scope', 'ngTableParams', 'brands', 'InventoryItemTypeService', function ($scope, ngTableParams, brands, InventoryItemTypeService) {

    $scope.filter = {};
    $scope.brands = brands;

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
        params.$params.term = composeSearchTerm();
        InventoryItemTypeService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function composeSearchTerm() {
      var term = '';
      if ($scope.filter.brandCode) {
        appendTerm('brandCode==' + $scope.filter.brandCode);
      }
      function appendTerm(termToAppend) {
        if (term.length) {
          term += ';';
        }
        term += termToAppend;
      }
      return term;
    }

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