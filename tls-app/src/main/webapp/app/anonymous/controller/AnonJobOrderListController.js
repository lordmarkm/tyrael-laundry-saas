define(function () {
  return ['$scope', '$stateParams', 'ngTableParams', 'JobOrderService', 'JobOrderStatusService',
          function ($scope, $stateParams, ngTableParams, JobOrderService, JobOrderStatusService) {

    $scope.customerName = $stateParams.customerName;
    $scope.customerCode = $stateParams.customerCode;

    //Tell the parent anon scope who the customer is
    $scope.$emit('setCustomer', {
      customerName: $scope.customerName,
      customerCode: $scope.customerCode
    });

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

    $scope.josLabel = function (code) {
      return JobOrderStatusService.toLabel(code);
    };

  }];
});