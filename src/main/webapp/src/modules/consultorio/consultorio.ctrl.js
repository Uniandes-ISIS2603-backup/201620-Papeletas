/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("consultorioModule");

    mod.controller("consultorioCtrl", ['$scope', '$state', '$stateParams', '$http', 'consultorioContext', function ($scope, $state, $stateParams, $http, context) {
            
            $scope.records = {};
            
            $http.get(context).then(function (response){
                $scope.records = response.data;
            },responseError);
            
            
            
            if ($stateParams.consultorioId !== null && $stateParams.consultorioId !== undefined) {
                
                id = $stateParams.consultorioId;
                
                $http.get(context + "/" + id).then(function(response) {
                     $scope.currentRecord = response.data;
                }, responseError);
            } else {
                
                $scope.currentRecord = {
                    id: undefined,
                    libre: false
                };
                
                $scope.alerts = [];
            }
                        
            this.saveRecord = function (id) {
                
                currentRecord = $scope.currentRecord;
                
                if (id == null) {
                    return $http.post(context, currentRecord).then(function() {
                        $state.go('consultorioList');
                    }, responseError);
                }
                else {
                    return $http.put(context + "/" + currentRecord.id, currentRecord).then(function () {
                        $state.go('consultorioList');
                    }, responseError);
                };
                
            };
            
            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if (id !== null) {
                    $http.delete(context + "/" + currentRecord.id).then(function () {
                        $state.go('consultorioList');
                    }, responseError);
                };
            };
            
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

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