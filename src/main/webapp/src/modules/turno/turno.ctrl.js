/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("turnoModule");

    mod.controller("turnoCtrl", ['$scope', '$state', '$stateParams', '$http', 'medicoContext',
        function ($scope, $state, $stateParams, $http, medicoContext) {

            // crea variable del path de turno e inicializa la lista de turnos
            $scope.turnoContext = '/turnos';
            $scope.records = {};
            // carga las reviews
            $http.get(medicoContext + "/" + $stateParams.medicoId + $scope.turnoContext).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            if ($stateParams.turnoId !== null && $stateParams.turnoId !== undefined) {
                // toma el id del parámetro
                id = $stateParams.medicoId;
                // obtiene el dato del recurso REST
                $http.get(medicoContext + "/" + $stateParams.medicoId +$scope.turnoContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);
            }else{
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    consultorioId: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    duracion: undefined,
                    duracionCitas: undefined,
                    fecha: new Date(),
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    medicoId: undefined /*Tipo Long. El valor se asigna en el backend*/
                };
                $scope.alerts = [];
            }
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id === null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(medicoContext + "/" + $stateParams.medicoId + $scope.turnoContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('turnoMList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(medicoContext + "/" + $stateParams.medicoId + $scope.turnoContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('turnoMList');
                            }, responseError);
                }
                ;
            };

            // -----------------------------------------------------------------
            // Funciones para manejar los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);
})(window.angular);


