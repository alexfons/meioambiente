(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipocontrato-ambiental', {
            parent: 'entity',
            url: '/tipocontrato-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipocontrato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipocontrato/tipocontratoesambiental.html',
                    controller: 'TipocontratoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipocontrato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipocontrato-ambiental-detail', {
            parent: 'tipocontrato-ambiental',
            url: '/tipocontrato-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipocontrato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipocontrato/tipocontrato-ambiental-detail.html',
                    controller: 'TipocontratoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipocontrato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipocontrato', function($stateParams, Tipocontrato) {
                    return Tipocontrato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipocontrato-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipocontrato-ambiental-detail.edit', {
            parent: 'tipocontrato-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocontrato/tipocontrato-ambiental-dialog.html',
                    controller: 'TipocontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipocontrato', function(Tipocontrato) {
                            return Tipocontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipocontrato-ambiental.new', {
            parent: 'tipocontrato-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocontrato/tipocontrato-ambiental-dialog.html',
                    controller: 'TipocontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                categoria: null,
                                descricao: null,
                                subcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipocontrato-ambiental', null, { reload: 'tipocontrato-ambiental' });
                }, function() {
                    $state.go('tipocontrato-ambiental');
                });
            }]
        })
        .state('tipocontrato-ambiental.edit', {
            parent: 'tipocontrato-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocontrato/tipocontrato-ambiental-dialog.html',
                    controller: 'TipocontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipocontrato', function(Tipocontrato) {
                            return Tipocontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipocontrato-ambiental', null, { reload: 'tipocontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipocontrato-ambiental.delete', {
            parent: 'tipocontrato-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocontrato/tipocontrato-ambiental-delete-dialog.html',
                    controller: 'TipocontratoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipocontrato', function(Tipocontrato) {
                            return Tipocontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipocontrato-ambiental', null, { reload: 'tipocontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
