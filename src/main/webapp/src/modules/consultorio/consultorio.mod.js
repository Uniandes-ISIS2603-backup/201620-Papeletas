/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("consultorioModule", ["ngMessages"]);
    mod.constant("consultorioContext", "api/consultorio");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/consultorio/';
            $urlRouterProvider.otherwise("/consultorioList");
     
            $stateProvider.state('consultorioList', {
                url: '/consultorio',
                views: {
                    'mainView': {
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'consultorio.list.html'
                    }
                }
            }).state('consultorioCreate', {
                url: '/consultorio/create',
                views: {
                    'mainView': {
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'consultorio.create.html'
                    }
                }

            }).state('consultorioEdit', {
                url: '/consultorio/:consultorioId',
                param: {
                    consultorioId: null
                },
                views: {
                    'mainView': {
                        controller: 'consultorioCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'consultorio.create.html'
                    }
                }
            }).state('consultorioDelete',{
                url:'/consultorio/delete/:consultorioId',
                param: {
                    consultorioId: null
                },
                views: {
                    'mainView': {
                        controller: 'consultorioCrtl',
                        controllerAs: 'crtl',
                        templateUrl: basePath + 'consultorio.delete.html'
                    }
                }
            });
            
        }]);
})(window.angular);