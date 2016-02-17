define(function () {
  return ['confirm', 'toaster', function (confirm, toaster) {
    this.items = [];
    this.add = function (inventoryItem, quantity) {
      if (this.contains(inventoryItem)) {
        toaster.pop('error', 'Item already in cart', 'That item is already in the shopping cart');
        return false;
      }
      this.items.push({
        inventoryItem: inventoryItem,
        quantity: quantity,
        price: inventoryItem.sellingPrice * quantity
      });
    };
    this.contains = function (inventoryItem) {
      var i, item;
      for (i = 0; i < this.items.length; i++) {
        item = this.items[i];
        if (item.inventoryItem.code === inventoryItem.code) {
          return true;
        }
      }
      return false;
    };
    this.removeItem = function (index) {
      this.items.splice(index, 1);
    };
    this.clearCart = function() {
      var shoppingCart = this;
      confirm.confirm('Confirm clear shopping cart', 'Remove all items from shopping cart?')
        .result.then(function (ok) {
          if (ok) {
            shoppingCart.items = [];
          } else {
            return false;
          }
        });
    };
    this.onCheckout = function () {
      this.items = [];
    };
    this.totalPrice = function () {
      var totalPrice = 0;
      for (var i in this.items) {
        totalPrice += this.items[i].price;
      }
      return totalPrice;
    };
  }]
});