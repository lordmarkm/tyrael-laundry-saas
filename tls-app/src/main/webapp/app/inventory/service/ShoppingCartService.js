define(function () {
  return ['confirm', function (confirm) {
    this.items = [];
    this.add = function (inventoryItem, quantity) {
      this.items.push({
        inventoryItem: inventoryItem,
        quantity: quantity,
        price: inventoryItem.sellingPrice * quantity
      });
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