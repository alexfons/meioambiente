(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('proposta-ambiental', {
            parent: 'entity',
            url: '/proposta-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.proposta.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/proposta/propostasambiental.html',
                    controller: 'PropostaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('proposta');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('proposta-ambiental-detail', {
            parent: 'proposta-ambiental',
            url: '/proposta-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.proposta.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/proposta/proposta-ambiental-detail.html',
                    controller: 'PropostaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('proposta');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Proposta', function($stateParams, Proposta) {
                    return Proposta.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'proposta-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('proposta-ambiental-detail.edit', {
            parent: 'proposta-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proposta/proposta-ambiental-dialog.html',
                    controller: 'PropostaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Proposta', function(Proposta) {
                            return Proposta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('proposta-ambiental.new', {
            parent: 'proposta-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proposta/proposta-ambiental-dialog.html',
                    controller: 'PropostaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                classificacao: null,
                                nota: null,
                                tipoproposta: null,
                                obs: null,
                                contrato: null,
                                habilitada: null,
                                arqlink: null,
                                idproposta: null,
                                numeroedital: null,
                                valorproposta: null,
                                valorrenegociado: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('proposta-ambiental', null, { reload: 'proposta-ambiental' });
                }, function() {
                    $state.go('proposta-ambiental');
                });
            }]
        })
        .state('proposta-ambiental.edit', {
            parent: 'proposta-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proposta/proposta-ambiental-dialog.html',
                    controller: 'PropostaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Proposta', function(Proposta) {
                            return Proposta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('proposta-ambiental', null, { reload: 'proposta-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('proposta-ambiental.delete', {
            parent: 'proposta-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proposta/proposta-ambiental-delete-dialog.html',
                    controller: 'PropostaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Proposta', function(Proposta) {
                            return Proposta.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('proposta-ambiental', null, { reload: 'proposta-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
