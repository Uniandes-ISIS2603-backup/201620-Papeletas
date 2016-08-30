/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
    var mod = ng.module("horarioModule", ["ngMessages", "ui.router"]);
    mod.constant("horarioContext", "api/horario");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/horario/';
            $urlRouterProvider.otherwise("/horarioList");
     
            $stateProvider.state('horarioList', {
                url: '/horario',
                views: {
                    'mainView': {
                        controller: 'horarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horario.list.html'
                    }
                }
            }).state('horarioCreate', {
                url: '/horario/create',
                views: {
                    'mainView': {
                        controller: 'horarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horario.create.html'
                    }
                }

            }).state('horarioEdit', {
                url: '/horario/:horarioId',
                param: {
                    horarioId: null
                },
                views: {
                    'mainView': {
                        controller: 'horarioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'horario.create.html'
                    }
                }
            });
        }]);
})(window.angular);