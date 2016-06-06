define(function () {
  return ['$scope', '$stateParams', 'ngTableParams', 'JobOrderService',
          function ($scope, $stateParams, ngTableParams, JobOrderService) {

    $scope.customerName = $stateParams.customerName;

    //List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
        params.$params.sort = "dateCreated,DESC";
        params.$params.term = composeSearchTerm();
        JobOrderService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function reloadTable() {
      if (table.page() == 1) {
        table.reload();
      } else {
        table.page(1);
      }
    }

    function composeSearchTerm() {
      var term = 'deleted==false', 
          customerCode = $stateParams.customerCode || $scope.filter.customerCode;
      appendTerm('customerCode==' + $stateParams.customerCode);
      function appendTerm(termToAppend) {
        if (term.length) {
          term += ';';
        }
        term += termToAppend;
      }
      return term;
    }

  }];
});