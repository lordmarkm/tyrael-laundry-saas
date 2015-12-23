define(function () {
  return ['$modal', function ($modal) {
    this.confirm = function (title, msg, okBtn, cancelBtn) {
      return $modal.open({
        backdrop: 'static',
        templateUrl: 'core/view/modal_generic_confirm.html',
        controller: ['$scope', '$modalInstance', function ($scope, $modalInstance) {
          $scope.title = title;
          $scope.msg = msg;
          $scope.okBtn = okBtn;
          $scope.cancelBtn = cancelBtn;
          $scope.ok = function () {
            $modalInstance.close(true);
          };
          $scope.cancel = function () {
            $modalInstance.close(false);
          };
        }]
      });
    };
    return this;
  }];
});