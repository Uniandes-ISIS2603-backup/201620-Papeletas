(function (ng) {

    var mod = ng.module("mainApp", [
        "ui.router",
        "citasModule",
        "consultorioModule",
        "especializacionModule",
        "horarioModule",
        "medicoModule",
        "pacienteModule",
        "ngMessages"
    ]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
        }]);

})(window.angular);