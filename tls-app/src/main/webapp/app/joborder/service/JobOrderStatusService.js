define(function () {
  return [function () {
    var statusMap = {
      'NEW': 'New',
      'CLEANED': 'Cleaned',
      'PAID_CLAIMED': 'Claimed',
      'CLOSED': 'Closed',
      'CANCELLED': 'Cancelled'
    };
    this.statusMap = function () {
      return statusMap;
    }
    this.toLabel = function (code) {
      switch (code) {
      case 'NEW':
        return 'New';
      case 'CLEANED':
        return 'Cleaned';
      case 'PAID_CLAIMED':
        return 'Claimed';
      default:
        return 'Not new';
      }
    }
    return this;
  }];
});