(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('lancamentos-ambiental', {
            parent: 'entity',
            url: '/lancamentos-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.lancamentos.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/lancamentos/lancamentosambiental.html',
                    controller: 'LancamentosAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lancamentos');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('lancamentos-ambiental-detail', {
            parent: 'lancamentos-ambiental',
            url: '/lancamentos-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.lancamentos.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/lancamentos/lancamentos-ambiental-detail.html',
                    controller: 'LancamentosAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('lancamentos');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Lancamentos', function($stateParams, Lancamentos) {
                    return Lancamentos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'lancamentos-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('lancamentos-ambiental-detail.edit', {
            parent: 'lancamentos-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/lancamentos/lancamentos-ambiental-dialog.html',
                    controller: 'LancamentosAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Lancamentos', function(Lancamentos) {
                            return Lancamentos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('lancamentos-ambiental.new', {
            parent: 'lancamentos-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/lancamentos/lancamentos-ambiental-dialog.html',
                    controller: 'LancamentosAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                datalan: null,
                                historico: null,
                                recurso: null,
                                tipomedicao: null,
                                idlancamentos: null,
                                nummedicao: null,
                                valorrdebito: null,
                                valorusdebito: null,
                                valorrcredito: null,
                                valoruscredito: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('lancamentos-ambiental', null, { reload: 'lancamentos-ambiental' });
                }, function() {
                    $state.go('lancamentos-ambiental');
                });
            }]
        })
        .state('lancamentos-ambiental.edit', {
            parent: 'lancamentos-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/lancamentos/lancamentos-ambiental-dialog.html',
                    controller: 'LancamentosAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Lancamentos', function(Lancamentos) {
                            return Lancamentos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('lancamentos-ambiental', null, { reload: 'lancamentos-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('lancamentos-ambiental.delete', {
            parent: 'lancamentos-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/lancamentos/lancamentos-ambiental-delete-dialog.html',
                    controller: 'LancamentosAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Lancamentos', function(Lancamentos) {
                            return Lancamentos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('lancamentos-ambiental', null, { reload: 'lancamentos-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
