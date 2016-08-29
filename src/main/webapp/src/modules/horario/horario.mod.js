/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mod = ng.module("horarioModule", ["ui-router"]);

mod.config('$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
    var basePath = 'src/modules/horario/';
    $urlRouterProvider.otherwise("/horario");
    $stateProvider
            .state('horario',{
                templateUrl: basePath + ""
            })
            .state('horario.list', {
                
            })
}
