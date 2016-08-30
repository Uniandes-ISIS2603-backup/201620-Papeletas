(function (ng)
{
    var mod = ng.module("especializacionModule", ["ui.router"]);
    mod.constant("especializacion", "api/especializacion");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/especializacion';
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
                    'mainView': {controller: 'citiesCtrl', controllerAs: 'ctrl', templateUrl: basePath + 'cities.create.html'}
                }
        })
    }])
})(window.angular);