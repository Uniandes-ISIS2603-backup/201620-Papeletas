(function (ng) {
    var mod = ng.module("listaEsperaModule");

    mod.controller("listaEsperaCtrl", ['$scope', '$state', '$stateParams', '$http', 'medicoContext', function ($scope, $state, $stateParams, $http, medicoContext) {
        $scope.listaEsperaContext = '/listaespera';
        $scope.records = {};
        
        $http.get(medicoContext + "/" + $stateParams.medicoId+ $scope.listaEsperaContext).then(function(response){
             $scope.records = response.data;    
         }, responseError);
         
                     if ($stateParams.citaId !== null && $stateParams.citaId !== undefined) {
                // toma el id del parámetro
                id = $stateParams.citaId;
                // obtiene el dato del recurso REST
               $http.get(medicoContext + "/" + $stateParams.medicoId +$scope.citasContext+  id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un citaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    fecha: '',
                    duracion: undefined,
                    medico: {
                        id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                        nombre: '' /*Tipo String*/,
                        disponibilidad:''
                    },
                    consultorio: {
                        idConsult: undefined /*Tipo Long. El valor se asigna en el backend*/,
                        numero: undefined
                    },
                    paciente: {
                        id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                        name: '' /*Tipo String*/,
                        lastName: '',
                        age: undefined,
                        satisfaction: undefined,
                    }        
                };
              
                $scope.alerts = [];
            }
         
         this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(medicoContext + "/" + $stateParams.medicoId + $scope.listaEsperaContext, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('listaEspera');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(medicoContext + "/" + $stateParams.medicoId + $scope.listaEsperaContext + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('listaEspera');
                        }, responseError);
                };
            };
            
            this.deleteRecord = function(record){
                return $http.delete(medicoContext + "/" + $stateParams.medicoId + $scope.listaEsperaContext+ "/" + id)
                        .then(function(){
                            $state.reload();
                },responseError);
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