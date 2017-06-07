(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fisicografico-ambiental', {
            parent: 'entity',
            url: '/fisicografico-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fisicografico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fisicografico/fisicograficosambiental.html',
                    controller: 'FisicograficoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fisicografico');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('fisicografico-ambiental-detail', {
            parent: 'fisicografico-ambiental',
            url: '/fisicografico-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fisicografico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fisicografico/fisicografico-ambiental-detail.html',
                    controller: 'FisicograficoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fisicografico');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Fisicografico', function($stateParams, Fisicografico) {
                    return Fisicografico.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fisicografico-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fisicografico-ambiental-detail.edit', {
            parent: 'fisicografico-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisicografico/fisicografico-ambiental-dialog.html',
                    controller: 'FisicograficoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fisicografico', function(Fisicografico) {
                            return Fisicografico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fisicografico-ambiental.new', {
            parent: 'fisicografico-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisicografico/fisicografico-ambiental-dialog.html',
                    controller: 'FisicograficoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                atacada: null,
                                extensao: null,
                                fim: null,
                                inicio: null,
                                npontos: null,
                                pontofim: null,
                                pontoinicio: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fisicografico-ambiental', null, { reload: 'fisicografico-ambiental' });
                }, function() {
                    $state.go('fisicografico-ambiental');
                });
            }]
        })
        .state('fisicografico-ambiental.edit', {
            parent: 'fisicografico-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisicografico/fisicografico-ambiental-dialog.html',
                    controller: 'FisicograficoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fisicografico', function(Fisicografico) {
                            return Fisicografico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fisicografico-ambiental', null, { reload: 'fisicografico-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fisicografico-ambiental.delete', {
            parent: 'fisicografico-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisicografico/fisicografico-ambiental-delete-dialog.html',
                    controller: 'FisicograficoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fisicografico', function(Fisicografico) {
                            return Fisicografico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fisicografico-ambiental', null, { reload: 'fisicografico-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
