define(function () {
  return ['$scope', 'ngTableParams', 'toaster', 'CustomerService', function ($scope, ngTableParams, toaster, CustomerService) {

    //List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
       params.$params.sort = 'name.surname,ASC';
       if ($scope.namefilter) {
         params.$params.term = $scope.namefilter;
       } else {
         delete params.$params.term;
       }
       CustomerService.get(params.$params, function(response) {
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

    $scope.deleteCustomerFromList = function (customer) {
      //just call the root controller function + give callback
      $scope.deleteCustomer(customer, function () {
        table.reload();
      });
    };

  }];
});