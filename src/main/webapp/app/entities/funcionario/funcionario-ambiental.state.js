(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('funcionario-ambiental', {
            parent: 'entity',
            url: '/funcionario-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.funcionario.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/funcionario/funcionariosambiental.html',
                    controller: 'FuncionarioAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('funcionario');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('funcionario-ambiental-detail', {
            parent: 'funcionario-ambiental',
            url: '/funcionario-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.funcionario.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/funcionario/funcionario-ambiental-detail.html',
                    controller: 'FuncionarioAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('funcionario');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Funcionario', function($stateParams, Funcionario) {
                    return Funcionario.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'funcionario-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('funcionario-ambiental-detail.edit', {
            parent: 'funcionario-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/funcionario/funcionario-ambiental-dialog.html',
                    controller: 'FuncionarioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Funcionario', function(Funcionario) {
                            return Funcionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('funcionario-ambiental.new', {
            parent: 'funcionario-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/funcionario/funcionario-ambiental-dialog.html',
                    controller: 'FuncionarioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                funcao: null,
                                idfuncionarios: null,
                                nomefuncionario: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('funcionario-ambiental', null, { reload: 'funcionario-ambiental' });
                }, function() {
                    $state.go('funcionario-ambiental');
                });
            }]
        })
        .state('funcionario-ambiental.edit', {
            parent: 'funcionario-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/funcionario/funcionario-ambiental-dialog.html',
                    controller: 'FuncionarioAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Funcionario', function(Funcionario) {
                            return Funcionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('funcionario-ambiental', null, { reload: 'funcionario-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('funcionario-ambiental.delete', {
            parent: 'funcionario-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/funcionario/funcionario-ambiental-delete-dialog.html',
                    controller: 'FuncionarioAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Funcionario', function(Funcionario) {
                            return Funcionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('funcionario-ambiental', null, { reload: 'funcionario-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
