(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pagfuncionario-ambiental', {
            parent: 'entity',
            url: '/pagfuncionario-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.pagfuncionario.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pagfuncionario/pagfuncionariosambiental.html',
                    controller: 'PagfuncionarioAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pagfuncionario');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pagfuncionario-ambiental-detail', {
            parent: 'pagfuncionario-ambiental',
            url: '/pagfuncionario-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.pagfuncionario.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pagfuncionario/pagfuncionario-ambiental-detail.html',
                    controller: 'PagfuncionarioAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('pagfuncionario');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Pagfuncionario', function($stateParams, Pagfuncionario) {
                    return Pagfuncionario.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pagfuncionario-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pagfuncionario-ambiental-detail.edit', {
            parent: 'pagfuncionario-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagfuncionario/pagfuncionario-ambiental-dialog.html',
                    controller: 'PagfuncionarioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pagfuncionario', function(Pagfuncionario) {
                            return Pagfuncionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pagfuncionario-ambiental.new', {
            parent: 'pagfuncionario-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagfuncionario/pagfuncionario-ambiental-dialog.html',
                    controller: 'PagfuncionarioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                datapagamento: null,
                                idpagfuncionarios: null,
                                nsolicitacao: null,
                                percentual: null,
                                salario: null,
                                salariocontribuicao: null,
                                salariototal: null,
                                valorus: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pagfuncionario-ambiental', null, { reload: 'pagfuncionario-ambiental' });
                }, function() {
                    $state.go('pagfuncionario-ambiental');
                });
            }]
        })
        .state('pagfuncionario-ambiental.edit', {
            parent: 'pagfuncionario-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagfuncionario/pagfuncionario-ambiental-dialog.html',
                    controller: 'PagfuncionarioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Pagfuncionario', function(Pagfuncionario) {
                            return Pagfuncionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pagfuncionario-ambiental', null, { reload: 'pagfuncionario-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pagfuncionario-ambiental.delete', {
            parent: 'pagfuncionario-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pagfuncionario/pagfuncionario-ambiental-delete-dialog.html',
                    controller: 'PagfuncionarioAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Pagfuncionario', function(Pagfuncionario) {
                            return Pagfuncionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pagfuncionario-ambiental', null, { reload: 'pagfuncionario-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
