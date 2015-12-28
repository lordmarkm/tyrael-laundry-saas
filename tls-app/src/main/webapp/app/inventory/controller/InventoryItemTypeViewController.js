define(function () {
  return ['$scope', 'inventoryItemType', function ($scope, inventoryItemType) {
    $scope.inventoryItemType = inventoryItemType;
  }];
});