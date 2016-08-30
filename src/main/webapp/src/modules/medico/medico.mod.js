(function (ng) {
    var mod = ng.module("medicoModule", ["ngMessages"]);
    mod.constant("medicoContext", "api/medico");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/medico/';
            $urlRouterProvider.otherwise("/medicoList");
     
            $stateProvider.state('medicoList', {
                url: '/medico',
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.list.html'
                    }
                }
            }).state('medicoCreate', {
                url: '/medico/create',
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.create.html'
                    }
                }

            }).state('medicoEdit', {
                url: '/medico/:medicoId',
                param: {
                    citaId: null
                },
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.create.html'
                    }
                }
            });
        }]);
})(window.angular);