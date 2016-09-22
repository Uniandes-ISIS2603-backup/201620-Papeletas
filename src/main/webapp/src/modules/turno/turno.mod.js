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
            $urlRouterProvider.otherwise("/turnoMList");
            $stateProvider.state('turnoMList', {
                url: '/turnos',
                parent: 'medicoEdit',
                views: {
                    'medicoInstanceView': {
                        controller: 'turnoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'turno.mList.html'
                    }
                }
            }).state('turnoMCreate', {
                url: '/turnos/create',
                parent: 'medicoEdit',
                views: {
                    'medicoInstanceView': {
                        controller: 'turnoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'turno.mCreate.html'
                    }
                }
            }).state('turnoMEdit', {
                url: '/turnos/{turnoId:int}/edit',
                parent: 'medicoEdit',
                views: {
                    'medicoInstanceView': {
                        controller: 'turnoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'turno.mCreate.html'
                    }
                }
            });
        }]);
})(window.angular);

