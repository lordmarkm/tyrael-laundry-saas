define(function () {
  return ['$scope', '$modal', 'ngTableParams', 'toaster', 'InventoryItemService',
          function ($scope, $modal, ngTableParams, toaster, InventoryItemService) {

    //List
    var table = $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2,5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //search
        params.$params.sort = 'dateUpdated,DESC';
        if ($scope.namefilter) {
          params.$params.term = $scope.namefilter;
        } else {
          delete params.$params.term;
        }
        InventoryItemService.get(params.$params, function(response) {
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

    //Restock inventory
    function showRestockModal(inventoryItem) {
      return $modal.open({
        templateUrl: 'inventory/view/modal-restock.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', function ($modalScope, $modalInstance) {
          $modalScope.restockQuantity = 0;
          $modalScope.inventoryItem = inventoryItem;
          $modalScope.proceed = function () {
            $modalInstance.close($modalScope.restockQuantity);
          };
          $modalScope.cancel = function () {
            $modalInstance.close(false);
          };
        }],
        resolve: {
        }
      });
    };

    $scope.restock = function (inventoryItem) {
      showRestockModal(inventoryItem).result.then(function (restockQuantity) {
        if (restockQuantity) {
          InventoryItemService.restock({inventoryItemCode: inventoryItem.code, quantity: restockQuantity}, {}, function (saved) {
            toaster.pop('success', 'Restock successful', 'Sucessfully restocked ' + inventoryItem.inventoryItemTypeName + ' by ' + restockQuantity);
            inventoryItem.quantity = saved.quantity;
          });
        }
      });
    };

    //Consume inventory (use up but not sell)
    function showConsumeModal(inventoryItem) {
      return $modal.open({
        templateUrl: 'inventory/view/modal-consume.html',
        background: 'static',
        controller: ['$scope', '$modalInstance', function ($modalScope, $modalInstance) {
          $modalScope.consumeQuantity = 0;
          $modalScope.inventoryItem = inventoryItem;
          $modalScope.proceed = function () {
            $modalInstance.close($modalScope.consumeQuantity);
          };
          $modalScope.cancel = function () {
            $modalInstance.close(false);
          };
        }],
        resolve: {
        }
      });
    };

    $scope.consume = function (inventoryItem) {
      showConsumeModal(inventoryItem).result.then(function (consumeQuantity) {
        if (consumeQuantity) {
          InventoryItemService.consume({inventoryItemCode: inventoryItem.code, quantity: consumeQuantity}, {}, function (saved) {
            toaster.pop('success', 'Consume successful', 'Sucessfully consumed ' + inventoryItem.inventoryItemTypeName + ' by ' + consumeQuantity);
            inventoryItem.quantity = saved.quantity;
          });
        }
      });
    };

  }];
});