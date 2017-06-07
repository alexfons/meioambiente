(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('planocontas-ambiental', {
            parent: 'entity',
            url: '/planocontas-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.planocontas.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/planocontas/planocontasambiental.html',
                    controller: 'PlanocontasAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('planocontas');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('planocontas-ambiental-detail', {
            parent: 'planocontas-ambiental',
            url: '/planocontas-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.planocontas.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/planocontas/planocontas-ambiental-detail.html',
                    controller: 'PlanocontasAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('planocontas');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Planocontas', function($stateParams, Planocontas) {
                    return Planocontas.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'planocontas-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('planocontas-ambiental-detail.edit', {
            parent: 'planocontas-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planocontas/planocontas-ambiental-dialog.html',
                    controller: 'PlanocontasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Planocontas', function(Planocontas) {
                            return Planocontas.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('planocontas-ambiental.new', {
            parent: 'planocontas-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planocontas/planocontas-ambiental-dialog.html',
                    controller: 'PlanocontasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                idplanocontas: null,
                                ncontabil: null,
                                descricao: null,
                                tipoconta: null,
                                tipolancamento: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('planocontas-ambiental', null, { reload: 'planocontas-ambiental' });
                }, function() {
                    $state.go('planocontas-ambiental');
                });
            }]
        })
        .state('planocontas-ambiental.edit', {
            parent: 'planocontas-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planocontas/planocontas-ambiental-dialog.html',
                    controller: 'PlanocontasAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Planocontas', function(Planocontas) {
                            return Planocontas.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('planocontas-ambiental', null, { reload: 'planocontas-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('planocontas-ambiental.delete', {
            parent: 'planocontas-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/planocontas/planocontas-ambiental-delete-dialog.html',
                    controller: 'PlanocontasAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Planocontas', function(Planocontas) {
                            return Planocontas.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('planocontas-ambiental', null, { reload: 'planocontas-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
