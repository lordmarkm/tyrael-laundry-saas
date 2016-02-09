define(function () {
  return ['$resource', function ($resource) {
    return $resource('/inv-item', {}, {
      restock: {
        url: '/inv-item/:inventoryItemCode/:quantity',
        method: 'PUT'
      },
      consume: {
        url: '/inv-item/consume/:inventoryItemCode/:quantity',
        method: 'PUT'
      }
    });
  }];
});