(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('valoresdesembolso-ambiental', {
            parent: 'entity',
            url: '/valoresdesembolso-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.valoresdesembolso.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/valoresdesembolso/valoresdesembolsosambiental.html',
                    controller: 'ValoresdesembolsoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('valoresdesembolso');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('valoresdesembolso-ambiental-detail', {
            parent: 'valoresdesembolso-ambiental',
            url: '/valoresdesembolso-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.valoresdesembolso.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/valoresdesembolso/valoresdesembolso-ambiental-detail.html',
                    controller: 'ValoresdesembolsoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('valoresdesembolso');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Valoresdesembolso', function($stateParams, Valoresdesembolso) {
                    return Valoresdesembolso.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'valoresdesembolso-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('valoresdesembolso-ambiental-detail.edit', {
            parent: 'valoresdesembolso-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/valoresdesembolso/valoresdesembolso-ambiental-dialog.html',
                    controller: 'ValoresdesembolsoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Valoresdesembolso', function(Valoresdesembolso) {
                            return Valoresdesembolso.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('valoresdesembolso-ambiental.new', {
            parent: 'valoresdesembolso-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/valoresdesembolso/valoresdesembolso-ambiental-dialog.html',
                    controller: 'ValoresdesembolsoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                datainternalizacao: null,
                                idvaloresdesembolso: null,
                                nsolicitacao: null,
                                tipodesembolso: null,
                                valoreais: null,
                                valorus: null,
                                valuedata: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('valoresdesembolso-ambiental', null, { reload: 'valoresdesembolso-ambiental' });
                }, function() {
                    $state.go('valoresdesembolso-ambiental');
                });
            }]
        })
        .state('valoresdesembolso-ambiental.edit', {
            parent: 'valoresdesembolso-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/valoresdesembolso/valoresdesembolso-ambiental-dialog.html',
                    controller: 'ValoresdesembolsoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Valoresdesembolso', function(Valoresdesembolso) {
                            return Valoresdesembolso.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('valoresdesembolso-ambiental', null, { reload: 'valoresdesembolso-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('valoresdesembolso-ambiental.delete', {
            parent: 'valoresdesembolso-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/valoresdesembolso/valoresdesembolso-ambiental-delete-dialog.html',
                    controller: 'ValoresdesembolsoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Valoresdesembolso', function(Valoresdesembolso) {
                            return Valoresdesembolso.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('valoresdesembolso-ambiental', null, { reload: 'valoresdesembolso-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
