angular.module('userApp.services', []).factory('User', function($resource) {
    return $resource('user/:id', { id: '@id' }, {
        update: {
            method: 'POST'
        }
    });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});