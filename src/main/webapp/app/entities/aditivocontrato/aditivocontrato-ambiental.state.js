(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('aditivocontrato-ambiental', {
            parent: 'entity',
            url: '/aditivocontrato-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.aditivocontrato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/aditivocontrato/aditivocontratoesambiental.html',
                    controller: 'AditivocontratoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('aditivocontrato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('aditivocontrato-ambiental-detail', {
            parent: 'aditivocontrato-ambiental',
            url: '/aditivocontrato-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.aditivocontrato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/aditivocontrato/aditivocontrato-ambiental-detail.html',
                    controller: 'AditivocontratoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('aditivocontrato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Aditivocontrato', function($stateParams, Aditivocontrato) {
                    return Aditivocontrato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'aditivocontrato-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('aditivocontrato-ambiental-detail.edit', {
            parent: 'aditivocontrato-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/aditivocontrato/aditivocontrato-ambiental-dialog.html',
                    controller: 'AditivocontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Aditivocontrato', function(Aditivocontrato) {
                            return Aditivocontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('aditivocontrato-ambiental.new', {
            parent: 'aditivocontrato-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/aditivocontrato/aditivocontrato-ambiental-dialog.html',
                    controller: 'AditivocontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                numaditivo: null,
                                tipoaditivo: null,
                                data: null,
                                prazoaditivo: null,
                                valor: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('aditivocontrato-ambiental', null, { reload: 'aditivocontrato-ambiental' });
                }, function() {
                    $state.go('aditivocontrato-ambiental');
                });
            }]
        })
        .state('aditivocontrato-ambiental.edit', {
            parent: 'aditivocontrato-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/aditivocontrato/aditivocontrato-ambiental-dialog.html',
                    controller: 'AditivocontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Aditivocontrato', function(Aditivocontrato) {
                            return Aditivocontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('aditivocontrato-ambiental', null, { reload: 'aditivocontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('aditivocontrato-ambiental.delete', {
            parent: 'aditivocontrato-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/aditivocontrato/aditivocontrato-ambiental-delete-dialog.html',
                    controller: 'AditivocontratoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Aditivocontrato', function(Aditivocontrato) {
                            return Aditivocontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('aditivocontrato-ambiental', null, { reload: 'aditivocontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
