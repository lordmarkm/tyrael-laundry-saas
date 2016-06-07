define(function () {

  return ['$scope', '$stateParams', 'joborder',
          function ($scope, $stateParams, joborder) {

    $scope.joborder = joborder;

  }];

});