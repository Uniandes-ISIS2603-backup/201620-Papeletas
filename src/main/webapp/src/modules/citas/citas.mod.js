(function (ng) {
    var mod = ng.module("citasModule", ["ngMessages", "ui.router"]);
    mod.constant("citasContext", "api/citas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/citas/';
            $urlRouterProvider.otherwise("/citasList");
     
            $stateProvider.state('citasList', {
                url: '/citas',
                views: {
                    'mainView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.list.html'
                    }
                }
            }).state('citaCreate', {
                url: '/citas/create',
                views: {
                    'mainView': {
                        controller: 'citasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'citas.create.html'
                    }
                }

            }).state('citaEdit', {
                url: '/citas/:citaId',
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
            });
        }]);
})(window.angular);