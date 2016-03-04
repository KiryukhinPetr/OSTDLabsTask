var app = angular.module("account.view", ['ui.bootstrap', 'ui.router']);

app.controller("accountView", function ($scope, $http, $modal, $stateParams, $state) {
    var urlGetAccountById = '/ostd/api/account/getById';
    var urlDeleteAccount = '/ostd/api/account/deleteById';
    $scope.selectedAccount = {};
    $scope.getAccountById = function(){
      var data = {
                  id: $stateParams.id
                 };
                 var config = {
                  params: data,
                  headers : {'Accept' : 'application/json'}
                 };

                 $http.get(urlGetAccountById, config).then(function(response) {
                 $scope.selectedAccount = response.data;
                  }, function(response) {
                 });
    }
    $scope.getAccountById();
    $scope.editAccount = function(){
           $state.go("accountEdit", {id: $stateParams.id});
    }
    $scope.deleteAccount = function(){
          var data = {
                      id: $stateParams.id
                     };
                     var config = {
                      params: data,
                      headers : {'Accept' : 'application/json'}
                     };
                     $http.delete(urlDeleteAccount, config).then(function(response) {
                     alert("Account ID: " +  $stateParams.id + " were successfully deleted." );
                     $state.go("accountList");
                      }, function(response) {
                     });
                }
});
app.controller("accountEdit", function ($scope, $http, $modal, $stateParams, $state) {
    var urlGetAccountById = '/ostd/api/account/getById';
    var urlDeleteAccount = '/ostd/api/account/deleteById';
    var urlUpdateAccount = '/ostd/api/account/update';
    $scope.selectedAccount = {};
    $scope.getAccountById = function(){
          var data = {
                      id: $stateParams.id
                     };
                     var config = {
                      params: data,
                      headers : {'Accept' : 'application/json'}
                     };

                     $http.get(urlGetAccountById, config).then(function(response) {
                     $scope.selectedAccount = response.data;
                     $scope.iban = response.data.iban;
                     $scope.bic = response.data.bic;
                      }, function(response) {
                     });
        }

    $scope.getAccountById();

    $scope.updateAccount = function(){
      var data = {
                  id: $stateParams.id,
                  iban: $scope.iban,
                  bic: $scope.bic
                 };
                 var config = {
                  params: data,
                  headers : {'Accept' : 'application/json'}
                 };
                 $http.get(urlUpdateAccount, config).then(function(response) {
                 alert("Account ID: " +  $stateParams.id + " were successfully updated." );
                 $state.go("accountList");
                  }, function(response) {
                 });
        }
    $scope.deleteAccount = function(){
      var data = {
                  id: $stateParams.id
                 };
                 var config = {
                  params: data,
                  headers : {'Accept' : 'application/json'}
                 };
                 $http.delete(urlDeleteAccount, config).then(function(response) {
                 alert("Account ID: " +  $stateParams.id + " were successfully deleted." );
                 $state.go("accountList");
                  }, function(response) {
                 });
            }
});