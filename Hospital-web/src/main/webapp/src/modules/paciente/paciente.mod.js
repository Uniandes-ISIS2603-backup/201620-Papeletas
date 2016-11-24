/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
var mod = angular.module("pacienteModule",["ngMessages","ui.router"]);
mod.constant("pacienteContext","api/pacientes");
mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/paciente/';
        $urlRouterProvider.otherwise("/pacientesList");
        $stateProvider.state('pacientesList',{
            url: '/pacientes',
            views: {
                'mainView': {
                    controller: 'pacienteCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'listl.html'
                }
            }
        }).state('pacienteCreate',{
            url: '/pacientes/create',
            views: {
                'mainView': {
                    controller: 'pacienteCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'paciente.create.html'
                }
            }
        }).state('pacienteEdit',{
            url: '/pacientes/:pacienteId/edit',
            param:{
                pacienteId: null
            },
            views: {
                'mainView': {
                    controller: 'pacienteCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'paciente.create.html'
                }
            }
        }).state('pacienteDelete',{
            url: '/pacientes/delete/:pacienteId',
            param:{
                pacienteId: null
            },
            views: {
                'mainView': {
                    controller: 'pacienteCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'paciente.delete.html'
                }
            }
        });
}]);
})(window.angular);
