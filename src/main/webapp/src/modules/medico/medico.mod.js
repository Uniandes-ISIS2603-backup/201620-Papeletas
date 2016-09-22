(function (ng) {
    var mod = ng.module("medicoModule", []);
    mod.constant("medicoContext", "api/medico");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/medico/';
            $urlRouterProvider.otherwise("/medicoList");
            
            $stateProvider.state('medico',{
                url:'/medicos',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medicos.html'
                    }
                }
            }).state('medicoList', {
                url: '/list',
                parent: 'medico',
                views: {
                    'medicoView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.list.html'
                    }
                }
            }).state('medicoCreate', {
                url: '/medico/create',
                parent: 'medico',
                views: {
                    'medicoView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.create.html'
                    }
                }
            }).state('medicoRegistrar', {
                url: '/{medicoId:int}/registrar',
                parent: 'medico',
                views: {
                    'medicoView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.registrar.html'
                    }
                }
            }).state('medicoEdit', {
                url: '/{medicoId:int}/edit',
                param: {
                    medicoId: null
                },
                parent: 'medico',
                views: {
                    'medicoView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.edit.html'
                    },
                     'childView': {
                        templateUrl: basePath + 'medico.instance.html'
                    }
                }
                }).state('medicoDelete', {
                url: '/medico/delete/:medicoId',
                param: {
                    medicoId: null
                },
                parent: 'medico',
                views: {
                    'medicoView': {
                        controller: 'medicoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'medico.delete.html'
                    }
                }
            });
        }]);
})(window.angular);