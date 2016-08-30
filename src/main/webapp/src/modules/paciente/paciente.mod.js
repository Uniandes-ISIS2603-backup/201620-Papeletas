/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module("pacienteModule",["ui.router"]);

mod.constant("pacienteContext","api/pacientes");
mod.config(['$stateProvider','$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/paciente/';
        $urlRouterProvider.otherwise("/pacientesList");
        $stateProvider.state('pacientesList',{
            url: '/pacientes',
            views: {
                'mainView': {
                    controller: 'paciente.ctrl.js',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'pacientes.list.html'
                }
            }
        }).state('pacienteCreate',{
            url: '/pacientes/create',
            views: {
                'mainView': {
                    controller: 'paciente.ctrl.js',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'pacientes.create.html'
                }
            }
        }).state('pacienteEdit',{
            url: '/pacientes/:pacienteId',
            param:{
                pacienteId: null
            },
            views: {
                'mainView': {
                    controller: 'paciente.ctrl.js',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'pacientes.create.html'
                }
            }
        }).state('pacienteDelete',{
            url: '/pacientes/:pacienteId',
            param:{
                pacienteId: null
            },
            views: {
                'mainView': {
                    controller: 'paciente.ctrl.js',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'pacientes.delete.html'
                }
            }
        });
}]);

