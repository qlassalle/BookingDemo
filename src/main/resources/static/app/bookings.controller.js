/**
 * Created by qlassalle on 02/03/2017.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookingsController', BookingsController);

    BookingsController.$inject = ['$http'];

    function BookingsController($http) {
        var vm = this;

        vm.bookings = [];
        vm.getAll = getAll;
        vm.getAffordable = getAffordable;
        vm.deleteBooking = deleteBooking;
        vm.createHotel = createHotel;

        init();

        function init() {
            getAll();
        }

        function createHotel(name, price, nbNights) {
            var hotel = {
                "hotelName": name,
                "pricePerNight": price,
                "nbOfNights": nbNights
            };
            var url = "/bookings/create";
            $http.post(url, hotel).then(function (response) {
                vm.bookings = response.data;
            });
        }

        function getAll() {
            var url = "/bookings/all";
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(function (response) {
                vm.bookings = response.data;
            });
        }

        function getAffordable() {
            var url = "/bookings/affordable/" + 100;
            var bookingsPromise = $http.get(url);
            bookingsPromise.then(function (response) {
                vm.bookings = response.data;
            });
        }

        function deleteBooking(id) {
            var url = "bookings/remove/" + id;
            $http.post(url).then(function (response) {
                vm.bookings = response.data;
            })
        }
    }
})();