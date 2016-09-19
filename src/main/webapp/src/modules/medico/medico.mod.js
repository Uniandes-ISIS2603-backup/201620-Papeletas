(function (ng) {
    var mod = ng.module("medicoModule", ["ngMessages", "ui.router"]);
    mod.constant("medicoContext", "api/medico");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/medico/';
            $urlRouterProvider.otherwise("/medicoList");
     
            $stateProvider.state('medicoList', {
                url: '/medicos',
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.list.html'
                    }
                }
            }).state('medicoCreate', {
                url: '/medicos/create',
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.create.html'
                    }
                }

            }).state('medicoEdit', {
                url: '/medicos/:medicoId',
                param: {
                    medicoId: null
                },
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.edit.html'
                    }
                }
                }).state('medicoDelete', {
                url: '/medicos/delete/:medicoId',
                param: {
                    medicoId: null
                },
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.delete.html'
                    }
                }
            });
        }]);
})(window.angular);