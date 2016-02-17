define(function () {
  return ['$scope', '$state', '$stateParams', '$modal', 'ngTableParams', 'toaster', 'moment', 'branches', 'SalesHeaderService',
          function ($scope, $state, $stateParams, $modal, ngTableParams, toaster, moment, branches, SalesHeaderService) {

    //Filters
    $scope.filter = {};
    $scope.itemCode = $stateParams.itemCode;
    $scope.itemName = $stateParams.itemName;
    $scope.branches = branches;
    $scope.dateFormat = 'yyyy MMM-dd';
    $scope.today = moment();

    $scope.clearFilters = function () {
      $scope.filter = {};
      reloadTable();
    };

    $scope.doFilter = function () {
      reloadTable();
    };

    $scope.open = function (picker, evt) {
      evt.preventDefault();
      evt.stopPropagation();
      
      if (picker === 'from') {
        $scope.openDatefrom = true;
      } else if (picker === 'to') {
        $scope.openDateto = true;
      }
    };

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
        SalesHeaderService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function composeSearchTerm() {
      var term = '', 
        itemCode = $stateParams.itemCode || $scope.filter.itemCode;
      if (itemCode) {
        appendTerm('itemCode==' + itemCode);
      }
      if ($scope.filter.branchCode) {
        appendTerm('branchCode==' + $scope.filter.branchCode);
      }
      if ($scope.filter.datefrom) {
        appendTerm('dateCreated>=' + moment($scope.filter.datefrom).format('YYYY-MM-DD'));
      }
      if ($scope.filter.dateto) {
        appendTerm('dateCreated<=' + moment($scope.filter.dateto).format('YYYY-MM-DD'));
      }
      function appendTerm(termToAppend) {
        if (term.length) {
          term += ';';
        }
        term += termToAppend;
      }
      return term;
    }

    function reloadTable() {
      if (table.page() === 1) {
        table.reload();
      } else {
        table.page(1);
      }
    }

  }];
});