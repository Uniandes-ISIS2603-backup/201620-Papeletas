/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("turnoModule", ["ngMessages", "ui.router"]);
    mod.constant("turnoContext", "api/turnos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/horarios/';
            $urlRouterProvider.otherwise("/horariosList");
            $stateProvider.state('horariosList', {
                url: '/horarios',
                views: {
                    'mainView': {
                        controller: 'horariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horarios.list.html'
                    }
                }
            }).state('horariosCreate', {
                url: '/horarios/create',
                views: {
                    'mainView': {
                        controller: 'horariosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horarios.create.html'
                    }
                }
            }).state('horariosEdit', {
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

