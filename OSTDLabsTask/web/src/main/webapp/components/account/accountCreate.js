var app = angular.module("account.create", ['ui.bootstrap', 'ui.router']);

app.controller("accountCreate", function ($scope, $http, $modal, $stateParams, $state) {
    var urlCreateAccount = '/ostd/api/account/create';
    $scope.createAccount = function(){
     var data = {
      iban: $scope.iban,
      bic: $scope.bic
     };
     var config = {
      params: data,
      headers : {'Accept' : 'application/json'}
     };

     $http.get(urlCreateAccount, config).then(function(response) {
        alert("Account ID: was successfully added to the DB." );
        $state.go("accountList");
      }, function(response) {
     });
    }
});
