/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("pacienteModule");

    mod.controller("pacientesCtrl", ['$scope', '$state', '$stateParams', '$http', 'pacienteContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de pacientes está vacio
            $scope.records = {};
            // carga los pacientes
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un Id ??
            // revisa los parámetros (ver el Id en la definición de la ruta)
            if ($stateParams.pacienteId !== null && $stateParams.pacienteId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.pacienteId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un Id
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    pacid: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    lastName: '',
                    age: undefined,
                    satisfaction: undefined,
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('pacientesList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('pacientesList');
                        }, responseError);
                };
            };
            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if(id!=null)
                {
                    return $http.delete(context+"/"+id,currentRecord)
                            .then(function () {
                             $scope.records = {};
                             $http.get(context).then(function(response){
                                 $scope.records = response. response.data;
                             },responseError);
                             $state.go('pacientesList');
                    },responseError);
  
                }
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

