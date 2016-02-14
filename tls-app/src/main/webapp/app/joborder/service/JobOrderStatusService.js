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
    this.statusList = function () {
      var statuses = [];
      for (var i in statusMap) {
        statuses.push({value: i, label: statusMap[i]});
      }
      return statuses;
    }
    this.toLabel = function (code) {
      switch (code) {
      case 'NEW':
        return 'New';
      case 'CLEANED':
        return 'Cleaned';
      case 'PAID_CLAIMED':
        return 'Claimed';
      case 'CANCELLED':
        return 'Cancelled';
      case 'CLOSED':
        return 'Closed';
      default:
        return 'Unknown code: ' + code;
      }
    }
    return this;
  }];
});