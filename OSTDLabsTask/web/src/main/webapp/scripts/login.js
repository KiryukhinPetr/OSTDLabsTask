var app = angular.module("login");

app.controller("loginCtrl", function ($scope, $http, $modal) {
    var urlGetPrefixes = '/navi/rest/prefixes/getPrefixes';
    var urlGetJndiByPrefixs = '/navi/rest/getJndiByPrefix';
    $scope.submit = function () {
    localStorage.userName = $scope.userName;
    };

    function getJndiByPrefix() {
            var responsePromise = $http({
                url: urlGetJndiByPrefixs,
                method: "POST",
                data: JSON.stringify({
                    destination: null,
                    isQueue: null, message: null,
                    correlationId: null, customList: null, subscriberTimeout: null, prefix: $scope.selectedServer.id,
                    destination: $scope.destination
                }),
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'}
            });
            responsePromise.success(function (data, status) {
                for (i = 0; i < data.length; i++) {
                    data[i].type = ((data[i].is_queue == 1) ? "queue" : "topic");
                    data[i].color = ((data[i].isWorkingConfig == true) ? "#F5FFFA" : "#FF0000");
                }
                $scope.records = data;
            });
            responsePromise.error(function (data, status) {
                alert("Error Code:" + status + ";\n " + data.error);
            });
        };

});