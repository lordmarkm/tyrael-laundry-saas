define(function () {
  return [function () {
    this.items = [];
    this.add = function (inventoryItem, quantity) {
      this.items.push({
        inventoryItem: inventoryItem,
        quantity: quantity
      });
    };
    this.removeItem = function (index) {
      this.items.splice(index, 1);
    };
    this.clearCart = function() {
      if (!confirm('Are you sure?')) {
        return false;
      }
      this.items = [];
    };
    this.onCheckout = function () {
      this.items = [];
    };
    this.totalPrice = function () {
      return 10;
    };
  }]
});