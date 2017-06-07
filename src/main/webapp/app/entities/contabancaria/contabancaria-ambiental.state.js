(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('contabancaria-ambiental', {
            parent: 'entity',
            url: '/contabancaria-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contabancaria.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contabancaria/contabancariasambiental.html',
                    controller: 'ContabancariaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contabancaria');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('contabancaria-ambiental-detail', {
            parent: 'contabancaria-ambiental',
            url: '/contabancaria-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.contabancaria.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/contabancaria/contabancaria-ambiental-detail.html',
                    controller: 'ContabancariaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('contabancaria');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Contabancaria', function($stateParams, Contabancaria) {
                    return Contabancaria.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'contabancaria-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('contabancaria-ambiental-detail.edit', {
            parent: 'contabancaria-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contabancaria/contabancaria-ambiental-dialog.html',
                    controller: 'ContabancariaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contabancaria', function(Contabancaria) {
                            return Contabancaria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contabancaria-ambiental.new', {
            parent: 'contabancaria-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contabancaria/contabancaria-ambiental-dialog.html',
                    controller: 'ContabancariaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                idcontabancaria: null,
                                nconta: null,
                                nbanco: null,
                                descricao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('contabancaria-ambiental', null, { reload: 'contabancaria-ambiental' });
                }, function() {
                    $state.go('contabancaria-ambiental');
                });
            }]
        })
        .state('contabancaria-ambiental.edit', {
            parent: 'contabancaria-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contabancaria/contabancaria-ambiental-dialog.html',
                    controller: 'ContabancariaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Contabancaria', function(Contabancaria) {
                            return Contabancaria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contabancaria-ambiental', null, { reload: 'contabancaria-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('contabancaria-ambiental.delete', {
            parent: 'contabancaria-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/contabancaria/contabancaria-ambiental-delete-dialog.html',
                    controller: 'ContabancariaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Contabancaria', function(Contabancaria) {
                            return Contabancaria.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('contabancaria-ambiental', null, { reload: 'contabancaria-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
