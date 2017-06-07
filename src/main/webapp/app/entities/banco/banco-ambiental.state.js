(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('banco-ambiental', {
            parent: 'entity',
            url: '/banco-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.banco.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/banco/bancosambiental.html',
                    controller: 'BancoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('banco');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('banco-ambiental-detail', {
            parent: 'banco-ambiental',
            url: '/banco-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.banco.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/banco/banco-ambiental-detail.html',
                    controller: 'BancoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('banco');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Banco', function($stateParams, Banco) {
                    return Banco.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'banco-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('banco-ambiental-detail.edit', {
            parent: 'banco-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/banco/banco-ambiental-dialog.html',
                    controller: 'BancoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Banco', function(Banco) {
                            return Banco.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('banco-ambiental.new', {
            parent: 'banco-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/banco/banco-ambiental-dialog.html',
                    controller: 'BancoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cidadedep: null,
                                cidadeint: null,
                                codswifdep: null,
                                codswifint: null,
                                contafeddep: null,
                                contafedint: null,
                                enderecodep: null,
                                enderecoint: null,
                                idbanco: null,
                                instrucoesespeciaisdep: null,
                                instrucoesespeciaisint: null,
                                nabadep: null,
                                nabaint: null,
                                ncontadep: null,
                                ncontaint: null,
                                nomebancodep: null,
                                nomebancoint: null,
                                paisdep: null,
                                paisint: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('banco-ambiental', null, { reload: 'banco-ambiental' });
                }, function() {
                    $state.go('banco-ambiental');
                });
            }]
        })
        .state('banco-ambiental.edit', {
            parent: 'banco-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/banco/banco-ambiental-dialog.html',
                    controller: 'BancoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Banco', function(Banco) {
                            return Banco.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('banco-ambiental', null, { reload: 'banco-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('banco-ambiental.delete', {
            parent: 'banco-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/banco/banco-ambiental-delete-dialog.html',
                    controller: 'BancoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Banco', function(Banco) {
                            return Banco.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('banco-ambiental', null, { reload: 'banco-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
