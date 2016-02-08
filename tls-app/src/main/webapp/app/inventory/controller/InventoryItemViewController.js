define(function () {
  return ['$scope', 'inventoryItem', function ($scope, inventoryItem) {
    $scope.inventoryItem = inventoryItem;
  }];
});