(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('responsavel-ambiental', {
            parent: 'entity',
            url: '/responsavel-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.responsavel.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/responsavel/responsavelsambiental.html',
                    controller: 'ResponsavelAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('responsavel');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('responsavel-ambiental-detail', {
            parent: 'responsavel-ambiental',
            url: '/responsavel-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.responsavel.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/responsavel/responsavel-ambiental-detail.html',
                    controller: 'ResponsavelAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('responsavel');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Responsavel', function($stateParams, Responsavel) {
                    return Responsavel.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'responsavel-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('responsavel-ambiental-detail.edit', {
            parent: 'responsavel-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel/responsavel-ambiental-dialog.html',
                    controller: 'ResponsavelAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Responsavel', function(Responsavel) {
                            return Responsavel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('responsavel-ambiental.new', {
            parent: 'responsavel-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel/responsavel-ambiental-dialog.html',
                    controller: 'ResponsavelAmbientalDialogController',
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
                    $state.go('responsavel-ambiental', null, { reload: 'responsavel-ambiental' });
                }, function() {
                    $state.go('responsavel-ambiental');
                });
            }]
        })
        .state('responsavel-ambiental.edit', {
            parent: 'responsavel-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel/responsavel-ambiental-dialog.html',
                    controller: 'ResponsavelAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Responsavel', function(Responsavel) {
                            return Responsavel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('responsavel-ambiental', null, { reload: 'responsavel-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('responsavel-ambiental.delete', {
            parent: 'responsavel-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/responsavel/responsavel-ambiental-delete-dialog.html',
                    controller: 'ResponsavelAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Responsavel', function(Responsavel) {
                            return Responsavel.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('responsavel-ambiental', null, { reload: 'responsavel-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
