define(function () {
  return ['$scope', 'ngTableParams', 'BrandService', function ($scope, ngTableParams, BrandService) {

    //List
    $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
        BrandService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

  }];
});