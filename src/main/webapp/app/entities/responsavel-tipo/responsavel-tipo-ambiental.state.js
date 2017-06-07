(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('responsavel-tipo-ambiental', {
            parent: 'entity',
            url: '/responsavel-tipo-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.responsavelTipo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/responsavel-tipo/responsavel-tiposambiental.html',
                    controller: 'ResponsavelTipoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('responsavelTipo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('responsavel-tipo-ambiental-detail', {
            parent: 'responsavel-tipo-ambiental',
            url: '/responsavel-tipo-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.responsavelTipo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/responsavel-tipo/responsavel-tipo-ambiental-detail.html',
                    controller: 'ResponsavelTipoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('responsavelTipo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'ResponsavelTipo', function($stateParams, ResponsavelTipo) {
                    return ResponsavelTipo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'responsavel-tipo-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('responsavel-tipo-ambiental-detail.edit', {
            parent: 'responsavel-tipo-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel-tipo/responsavel-tipo-ambiental-dialog.html',
                    controller: 'ResponsavelTipoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ResponsavelTipo', function(ResponsavelTipo) {
                            return ResponsavelTipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('responsavel-tipo-ambiental.new', {
            parent: 'responsavel-tipo-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel-tipo/responsavel-tipo-ambiental-dialog.html',
                    controller: 'ResponsavelTipoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                email: null,
                                funcao: null,
                                localtrabalho: null,
                                matricula: null,
                                nome: null,
                                superintendencia: null,
                                telefone: null,
                                telefonecomercial: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('responsavel-tipo-ambiental', null, { reload: 'responsavel-tipo-ambiental' });
                }, function() {
                    $state.go('responsavel-tipo-ambiental');
                });
            }]
        })
        .state('responsavel-tipo-ambiental.edit', {
            parent: 'responsavel-tipo-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel-tipo/responsavel-tipo-ambiental-dialog.html',
                    controller: 'ResponsavelTipoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['ResponsavelTipo', function(ResponsavelTipo) {
                            return ResponsavelTipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('responsavel-tipo-ambiental', null, { reload: 'responsavel-tipo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('responsavel-tipo-ambiental.delete', {
            parent: 'responsavel-tipo-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel-tipo/responsavel-tipo-ambiental-delete-dialog.html',
                    controller: 'ResponsavelTipoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['ResponsavelTipo', function(ResponsavelTipo) {
                            return ResponsavelTipo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('responsavel-tipo-ambiental', null, { reload: 'responsavel-tipo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
