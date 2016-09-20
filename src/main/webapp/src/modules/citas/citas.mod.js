(function (ng) {
    var mod = ng.module("citasModule", ["ngMessages", "ui.router"]);
    mod.constant("citasContext", "api/citas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citas/';
            $urlRouterProvider.otherwise("/citasList");
     
            $stateProvider.state('citasList', {
                url: '/citas',
                parent: 'medicoEdit',
                views: {
                    'medicoInstanceView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.list.html'
                    }
                }
            }).state('citasCreate', {
                url: '/citas/create',
                views: {
                    'mainView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.create.html'
                    }
                }

            }).state('citasEdit', {
                url: '/citas/delete/:citaId',
                param: {
                    citaId: null
                },
                views: {
                    'mainView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.create.html'
                    }
                }
                }).state('citasReservar', {
                url: '/citas/reservar/:citaId',
                param: {
                    citaId: null
                },
                views: {
                    'mainView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.reservar.html'
                    }
                }
                }).state('citasDelete', {
                url: '/citas/:citaId',
                param: {
                    citaId: null
                },
                views: {
                    'mainView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.delete.html'
                    }
                }
            });
        }]);
})(window.angular);