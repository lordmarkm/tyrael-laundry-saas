define(function () {
  return ['$scope', 'ngTableParams', 'CustomerService', function ($scope, ngTableParams, CustomerService) {

    //List
    $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
       CustomerService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

  }];
});