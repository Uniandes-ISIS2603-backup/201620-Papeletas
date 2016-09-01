(function (ng) {
    var mod = ng.module("horariosModule");
    mod.controller("horariosCtrl", ['$scope', '$state', '$stateParams', '$http', 'horariosContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de horario está vacio
            $scope.records = {};
            // carga las horario
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibió un horarioId ??
            // revisa los parámetros (ver el :horarioId en la definición de la ruta)
            if ($stateParams.horarioId !== null && $stateParams.horarioId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.horarioId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibió un horarioId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    nombre: '' /*Tipo String*/,
                    tipo: '',
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                //verifica si el id dado existe
                var existe = false;
                for(var i = 0; i < $scope.records.length; i++){
                    if($scope.records[i].id === id){
                        existe = true;
                    }
                }
                if (existe == false) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('horariosList');
                            }, responseError);

                    // si el registro existe
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('horariosList');
                            }, responseError);
                }
                ;
            };

            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                $http.delete(context + "/" + currentRecord.id, currentRecord.id)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('horariosList');
                        }, responseError);
            };



            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


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