var mod = ng.module("horarioModule");

mod.controller('horarioController', ['$scope', '$state', '$stateParams', '$http', function ($scope, $state, $stateParams, $http) {
        this.listaHorarios = function () {
            $scope.horarios = {};
            $http.get('http://localhost:8080/Papeletas/api/horario').
                    then(function (response) {
                        $scope.horarios = response.data;
                    }),
                    then(function (response) {
                        //TODO: si algo sale mal
                    });
        }
    }]);