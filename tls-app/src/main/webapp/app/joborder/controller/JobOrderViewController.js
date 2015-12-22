define(function () {
  return ['$scope', 'joborder', 'jobItemTypes', function ($scope, joborder, jobItemTypes) {
    $scope.joborder = joborder;
    $scope.getJobItemType = function (jobItemTypeCode) {
      for (var i in jobItemTypes) {
        if (jobItemTypes[i].code === jobItemTypeCode) {
          return jobItemTypes[i];
        }
      }
    };
  }];
});