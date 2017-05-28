(function () {
    'use strict';

    angular
        .module('app')
        .controller('TicketsController', TicketsController);

    TicketsController.$inject = ['$http'];

    function TicketsController($http) {
        var vm = this;

        vm.tickets = [];
        vm.getAllTickets = getAllTickets;
        vm.cancelTicket = cancelTicket;
        vm.ticketDetails = ticketDetails;

        init();

        function init(){
            getAllTickets();
        }

        function getAllTickets(){
            var url = "/tickets/list_tickets";
            var tic = $http.get(url);
            tic.then(function(response){
                vm.tickets = response.data;
            });
        }

        function cancelTicket(id){
            var url = "/tickets/cancel_ticket/"+id;
            $http.post(url).then(function(response){
               vm.tickets = response.data;
               location.href = '/tickets';
            });
        }

        function ticketDetails(id){
            var url = "/tickets/ticket_details/"+id;
            var tic = $http.get(url);
            tic.then(function(response){
                vm.tickets = response.data;
                location.href=url;
            });
        }

    }
})();