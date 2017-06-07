(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('empresacontrato-ambiental', {
            parent: 'entity',
            url: '/empresacontrato-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.empresacontrato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/empresacontrato/empresacontratoesambiental.html',
                    controller: 'EmpresacontratoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('empresacontrato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('empresacontrato-ambiental-detail', {
            parent: 'empresacontrato-ambiental',
            url: '/empresacontrato-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.empresacontrato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/empresacontrato/empresacontrato-ambiental-detail.html',
                    controller: 'EmpresacontratoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('empresacontrato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Empresacontrato', function($stateParams, Empresacontrato) {
                    return Empresacontrato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'empresacontrato-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('empresacontrato-ambiental-detail.edit', {
            parent: 'empresacontrato-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresacontrato/empresacontrato-ambiental-dialog.html',
                    controller: 'EmpresacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Empresacontrato', function(Empresacontrato) {
                            return Empresacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('empresacontrato-ambiental.new', {
            parent: 'empresacontrato-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresacontrato/empresacontrato-ambiental-dialog.html',
                    controller: 'EmpresacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tipo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('empresacontrato-ambiental', null, { reload: 'empresacontrato-ambiental' });
                }, function() {
                    $state.go('empresacontrato-ambiental');
                });
            }]
        })
        .state('empresacontrato-ambiental.edit', {
            parent: 'empresacontrato-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresacontrato/empresacontrato-ambiental-dialog.html',
                    controller: 'EmpresacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Empresacontrato', function(Empresacontrato) {
                            return Empresacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('empresacontrato-ambiental', null, { reload: 'empresacontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('empresacontrato-ambiental.delete', {
            parent: 'empresacontrato-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresacontrato/empresacontrato-ambiental-delete-dialog.html',
                    controller: 'EmpresacontratoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Empresacontrato', function(Empresacontrato) {
                            return Empresacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('empresacontrato-ambiental', null, { reload: 'empresacontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
