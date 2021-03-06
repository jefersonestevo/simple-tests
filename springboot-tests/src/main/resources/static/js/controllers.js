angular.module('userApp.controllers', []).controller('UserListController', function($scope, $state, popupService, $window, User) {
    $scope.users = User.query(); //fetch all users. Issues a GET to /api/users

    $scope.deleteUser = function(user) { // Delete a user. Issues a DELETE to /api/users/:id
        if (popupService.showPopup('Really delete this?')) {
            user.$delete(function() {
                $window.location.href = ''; //redirect to home
            });
        }
    };
}).controller('UserViewController', function($scope, $stateParams, User) {
    $scope.user = User.get({ id: $stateParams.id }); //Get a single user.Issues a GET to /api/users/:id
}).controller('UserCreateController', function($scope, $state, $stateParams, User) {
    $scope.user = new User();  //create new user instance. Properties will be set via ng-model on UI

    $scope.addUser = function() { //create a new user. Issues a POST to /api/users
        $scope.user.$save(function() {
            $state.go('users'); // on success go back to home i.e. users state.
        });
    };
}).controller('UserEditController', function($scope, $state, $stateParams, User) {
    $scope.updateUser = function() { //Update the edited user. Issues a PUT to /api/users/:id
        $scope.user.$update(function() {
            $state.go('users'); // on success go back to home i.e. users state.
        });
    };

    $scope.loadUser = function() { //Issues a GET request to /api/users/:id to get a user to update
        $scope.user = User.get({ id: $stateParams.id });
    };

    $scope.loadUser(); // Load a user which can be edited on UI
});