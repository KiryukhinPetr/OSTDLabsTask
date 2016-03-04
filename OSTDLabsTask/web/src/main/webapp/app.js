'use strict';

var myApp = angular.module('ostdApp', ['ui.router',
    'account.list',
    'account.create',
    'account.view']);

myApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("");
    $stateProvider
        .state('accountView', {
            url: "/account/view/:id",
            controller: "accountView",
            templateUrl: "components/account/account.view.html"
        })
        .state('accountEdit', {
            url: "/account/edit/:id",
            controller: "accountEdit",
            templateUrl: "components/account/account.edit.html"
        })
        .state('accountList', {
            url: '/account/list',
            controller: 'accountList',
            templateUrl: 'components/account/account.list.html'
        })
        .state('accountCreate', {
            url: 'account/create',
            controller: 'accountCreate',
            templateUrl: 'components/account/account.create.html'
        })
});