/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("especializacionModule");

    mod.controller("specCTRL", ['$scope', '$state', '$stateParams', '$http', 'especializacionContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de especializaciones está vacio
            $scope.records = {};
            // carga las especializacion
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            if ($stateParams.Id !== null && $stateParams.Id !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.Id;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un Id
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo int*/,
                    name: '' /*Tipo String*/
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es 0, es un registro nuevo, entonces lo crea
                if (id === 0) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('especializacionList');
                        }, responseError);
                        
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // cuando termine bien, cambie de estado
                            $state.go('especializacionList');
                        }, responseError);
                };
            };
            this.deleteRecord = function(currentRecord) {
                //currentRecord = $scope.currentRecord;
                $http.delete(context + "/" + currentRecord.id)
                        .then(function() {
                            $state.go('especializacionList');
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

