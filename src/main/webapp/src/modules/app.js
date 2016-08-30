(function (ng) {

    var mod = ng.module("mainView", [
        "ui.router",
        "citasModule",
        "pacienteModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/citasList');
        }]);
    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/pacientesList');
        }]);
  
})(window.angular);