(function (ng)
{
    var mod = ng.module("especializacionModule", ["ngMessages","ui.router"]);
    mod.constant("especializacionContext", "api/especializacion");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/especializacion/';
        $urlRouterProvider.otherwise("/especializacionList");
        $stateProvider.state('especializacionList',
        {
            url: '/especializacion', views:
                    {
                        'mainView':{controller:'specCTRL', controllerAs:'CTRL', templateUrl: basePath + 'especializacion.list.html'}
                    }
        }).state('especializacionCreate',
        {
                url: '/especializacion/create',
                views: 
                {
                    'mainView': {controller: 'specCTRL', controllerAs: 'CTRL', templateUrl: basePath + 'especializacion.create.html'}
                }
        }).state('especializacionEdit',
        {
                url: '/especializacion/:Id',
                param:
                {
                    especializacionId:null
                },
                views: 
                {
                    'mainView': {controller: 'specCTRL', controllerAs: 'CTRL', templateUrl: basePath + 'especializacion.create.html'}
                }
        })
    }])
})(window.angular);