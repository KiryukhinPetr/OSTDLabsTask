var app = angular.module("account.list", ['ui.bootstrap', 'ui.router']);

app.controller("accountList", function ($scope, $http, $modal) {
    var urlGetAllAccounts = '/ostd/api/account/getAll';
    function getAllAccounts() {
             var responsePromise = $http({
                    url: urlGetAllAccounts,
                    method: "GET",
                    headers: {'Accept': 'application/json', 'Content-Type': 'application/json'}
                });
                responsePromise.success(function (data, status) {
                    $scope.records = data;
                });
                responsePromise.error(function (data, status) {
                    alert("Error Code:" + status + ";\n " + data.error);
                });
            } ;

    $scope.records = [];
    getAllAccounts();
    $scope.currentPage = 1;
    $scope.numPerPage = 50;
    $scope.maxSize = 5;
    $scope.itemsPerPage = 50;
    $scope.predicate = 'id';
    $scope.reverse = false;
    $scope.order = function(predicate) {
            $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;
            $scope.predicate = predicate;
          };
});