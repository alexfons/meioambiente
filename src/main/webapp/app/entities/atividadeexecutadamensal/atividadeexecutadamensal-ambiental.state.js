(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atividadeexecutadamensal-ambiental', {
            parent: 'entity',
            url: '/atividadeexecutadamensal-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.atividadeexecutadamensal.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atividadeexecutadamensal/atividadeexecutadamensalsambiental.html',
                    controller: 'AtividadeexecutadamensalAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('atividadeexecutadamensal');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('atividadeexecutadamensal-ambiental-detail', {
            parent: 'atividadeexecutadamensal-ambiental',
            url: '/atividadeexecutadamensal-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.atividadeexecutadamensal.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atividadeexecutadamensal/atividadeexecutadamensal-ambiental-detail.html',
                    controller: 'AtividadeexecutadamensalAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('atividadeexecutadamensal');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Atividadeexecutadamensal', function($stateParams, Atividadeexecutadamensal) {
                    return Atividadeexecutadamensal.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atividadeexecutadamensal-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atividadeexecutadamensal-ambiental-detail.edit', {
            parent: 'atividadeexecutadamensal-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadeexecutadamensal/atividadeexecutadamensal-ambiental-dialog.html',
                    controller: 'AtividadeexecutadamensalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Atividadeexecutadamensal', function(Atividadeexecutadamensal) {
                            return Atividadeexecutadamensal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atividadeexecutadamensal-ambiental.new', {
            parent: 'atividadeexecutadamensal-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadeexecutadamensal/atividadeexecutadamensal-ambiental-dialog.html',
                    controller: 'AtividadeexecutadamensalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                percentualatacado: null,
                                fim: null,
                                inicio: null,
                                lado: null,
                                percentualconcluido: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('atividadeexecutadamensal-ambiental', null, { reload: 'atividadeexecutadamensal-ambiental' });
                }, function() {
                    $state.go('atividadeexecutadamensal-ambiental');
                });
            }]
        })
        .state('atividadeexecutadamensal-ambiental.edit', {
            parent: 'atividadeexecutadamensal-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadeexecutadamensal/atividadeexecutadamensal-ambiental-dialog.html',
                    controller: 'AtividadeexecutadamensalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Atividadeexecutadamensal', function(Atividadeexecutadamensal) {
                            return Atividadeexecutadamensal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atividadeexecutadamensal-ambiental', null, { reload: 'atividadeexecutadamensal-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atividadeexecutadamensal-ambiental.delete', {
            parent: 'atividadeexecutadamensal-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadeexecutadamensal/atividadeexecutadamensal-ambiental-delete-dialog.html',
                    controller: 'AtividadeexecutadamensalAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Atividadeexecutadamensal', function(Atividadeexecutadamensal) {
                            return Atividadeexecutadamensal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atividadeexecutadamensal-ambiental', null, { reload: 'atividadeexecutadamensal-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
