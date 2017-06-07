(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('projeto-ambiental', {
            parent: 'entity',
            url: '/projeto-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.projeto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projeto/projetosambiental.html',
                    controller: 'ProjetoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projeto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('projeto-ambiental-detail', {
            parent: 'projeto-ambiental',
            url: '/projeto-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.projeto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/projeto/projeto-ambiental-detail.html',
                    controller: 'ProjetoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('projeto');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Projeto', function($stateParams, Projeto) {
                    return Projeto.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'projeto-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('projeto-ambiental-detail.edit', {
            parent: 'projeto-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projeto/projeto-ambiental-dialog.html',
                    controller: 'ProjetoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projeto', function(Projeto) {
                            return Projeto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projeto-ambiental.new', {
            parent: 'projeto-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projeto/projeto-ambiental-dialog.html',
                    controller: 'ProjetoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                andamento: null,
                                pendente: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('projeto-ambiental', null, { reload: 'projeto-ambiental' });
                }, function() {
                    $state.go('projeto-ambiental');
                });
            }]
        })
        .state('projeto-ambiental.edit', {
            parent: 'projeto-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projeto/projeto-ambiental-dialog.html',
                    controller: 'ProjetoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Projeto', function(Projeto) {
                            return Projeto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projeto-ambiental', null, { reload: 'projeto-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('projeto-ambiental.delete', {
            parent: 'projeto-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/projeto/projeto-ambiental-delete-dialog.html',
                    controller: 'ProjetoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Projeto', function(Projeto) {
                            return Projeto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('projeto-ambiental', null, { reload: 'projeto-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
