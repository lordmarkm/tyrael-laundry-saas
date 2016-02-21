define(function () {
  return ['$scope', 'ngTableParams', 'AcctsPayableService', 'branches', function ($scope, ngTableParams, AcctsPayableService, branches) {

    $scope.filter = {};
    $scope.branches = branches;

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
       params.$params.term = composeSearchTerm();
       AcctsPayableService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function composeSearchTerm() {
      var term = '';
      if ($scope.filter.branchCode) {
        appendTerm('branchCode==' + $scope.filter.branchCode);
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
      console.debug(table.page());
      if (table.page() === 1) {
        table.reload();
      } else {
        table.page(1);
      }
    };

    $scope.clearFilter = function () {
      $scope.filter = {};
      $scope.doFilter();
    };

  }];
});