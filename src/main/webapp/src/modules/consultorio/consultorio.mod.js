/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("consultorioModule", ["ui-router"]);

    mod.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
        var basePath = 'src/modules/consultorio';
        $urlRouterProvider.otherwise("/consultorioList");
    
        $stateProvider.state("consultorioList", {
            url: "/consultorio",
            views: {
                "mainView" : {
                  controller : "consultoriosCtrl",
                  controllerAs: "ctrl",
                  templateURL: basePath + "consultorio.list.html"
                }
            }
        }).state("consultorioCreate", {
            url: "/consultorio/create",
            views: {
                "mainView" : {
                    controller : "consultoriosCtrl",
                    controllerAs: "ctrl",
                    templateURL: basePath + "consultorio.create.html"
                }
            }
        }).state("consultorioDelete", {
            url: "/consultorio/:consultorioId",
            param: {
                consultorioId : null
            },
            views: {
                "mainView" : {
                    controller : "consultoriosCtrl",
                    controllerAs : "ctrl",
                    templateURL : basePath + "consultorio.list.html"
                }
            }
        }).state("consultorioEdit", {
            url: "/consultorio/:consultorioId",
            param: {
                consultorioId : null
            },
            views : {
                "mainView" : {
                    controller : "consultoriosCrtl",
                    controllerAs : "ctrl",
                    templateURL : basePath + "consultorio.create.html"
                }
            }
        }) 
    }]);
})(window.angular);