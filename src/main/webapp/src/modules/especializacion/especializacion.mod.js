(function (ng)
{
    var mod = ng.module("especializacionModule", ["ui.router"]);
    mod.constant("especializacionContext", "api/especializacion");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/especializacion/';
        $urlRouterProvider.otherwise("/especializacionList");
        $stateProvider.state('especializacionList',
        {
            url: '/especializacion/', views:
                    {
                        'mainView':{controller:'specCTRL', controllerAs:'CTRL', templateUrl: basePath + 'especializacion.list.html'}
                    }
        }).state('especializacionCreate',
        {
                url: '/especializacion/',
                views: 
                {
                    'mainView': {controller: 'specCTRL', controllerAs: 'CTRL', templateUrl: basePath + 'especializacion.create.html'}
                }
        })
    }])
})(window.angular);