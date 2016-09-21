/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("turnoModule", ["ngMessages", "ui.router"]);
    mod.constant("turnoContext", "/turnos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/turno/';
            $urlRouterProvider.otherwise("/turnosMedicoList");
            $stateProvider.state('turnosMedicoList', {
                url: '/turnos',
                views: {
                    'mainView': {
                        controller: 'turnoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'turno.Mlist.html'
                    }
                }
            }).state('turnoMList', {
                url: '/listaMedico', 
                views: {
                    controller: 'turnoCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'turno. Mlist.html'
                }
            }).state('turnoCreate', {
                url: '/medico/turno/create',
                views: {
                    'mainView': {
                        controller: 'turnoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'turno.create.html'
                    }
                }
            }).state('turnoEdit', {
                url: '/horarios/:horarioId',
                param: {
                    horarioId: null
                },
                views: {
                    'mainView': {
                        controller: 'horariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horarios.create.html'
                    }
                }
            }).state('horariosDelete', {
                url: '/horarios/delete/:horarioId',
                param: {
                    horarioId: null
                },
                views: {
                    'mainView': {
                        controller: 'horariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horarios.delete.html'
                    }
                }
            });
        }]);
})(window.angular);

