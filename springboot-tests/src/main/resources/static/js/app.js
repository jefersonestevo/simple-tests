angular.module('userApp', ['ui.router', 'ngResource', 'userApp.controllers', 'userApp.services']);

angular.module('userApp').config(function($stateProvider) {
    $stateProvider.state('users', { // state for showing all user
        url: '/users',
        templateUrl: 'partials/users.html',
        controller: 'UserListController'
    }).state('viewUser', { //state for showing single user
        url: '/users/:id/view',
        templateUrl: 'partials/user-view.html',
        controller: 'UserViewController'
    }).state('newUser', { //state for adding a new user
        url: '/users/new',
        templateUrl: 'partials/user-add.html',
        controller: 'UserCreateController'
    }).state('editUser', { //state for updating a user
        url: '/users/:id/edit',
        templateUrl: 'partials/user-edit.html',
        controller: 'UserEditController'
    });
}).run(function($state) {
    $state.go('users'); //make a transition to users state when app starts
});
