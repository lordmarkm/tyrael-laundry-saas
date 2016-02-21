define(function () {
  return ['$scope', 'acctspayable', 'moment', 'ngTableParams', 'AcctsPaymentService',
          function ($scope, acctspayable, moment, ngTableParams, AcctsPaymentService) {
    $scope.acctspayable = acctspayable;
    $scope.moment = moment;

    //Payment List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
       //search
       params.$params.sort = 'paymentDate,DESC';
       params.$params.term = 'acctsPayableCode==' + acctspayable.code;
       AcctsPaymentService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    acctspayable.$promise.then(function () {
      table.reload();
    });

    //Make payment
    $scope.makePaymentFromViewPage = function (acctsPayable) {
      $scope.makePayment(acctsPayable, function () {
        table.reload();
      });
    };

  }];
});