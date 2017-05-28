(function () {
    'use strict';
    angular
        .module('app')
        .controller('EventsController', EventsController);
    EventsController.$inject = ['$http'];
    function EventsController($http) {
        var vm = this;


        vm.events = [];
        vm.getAllEvents = getAllEvents;
        vm.buyTicket = buyTicket;

        init();

        function init(){
            getAllEvents();
        }

        function getAllEvents(){
            var url = "/events/list_events";
            var ev = $http.get(url);
            ev.then(function(response){
                vm.events = response.data;
            });

        }

        function buyTicket(id){
            var url = "/events/buy_ticket/"+id;
            $http.post(url).then(function(response){
                vm.events = response.data;
            });
        }
    }
})();