(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atividadelicenca-ambiental', {
            parent: 'entity',
            url: '/atividadelicenca-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.atividadelicenca.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atividadelicenca/atividadelicencasambiental.html',
                    controller: 'AtividadelicencaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('atividadelicenca');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('atividadelicenca-ambiental-detail', {
            parent: 'atividadelicenca-ambiental',
            url: '/atividadelicenca-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.atividadelicenca.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atividadelicenca/atividadelicenca-ambiental-detail.html',
                    controller: 'AtividadelicencaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('atividadelicenca');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Atividadelicenca', function($stateParams, Atividadelicenca) {
                    return Atividadelicenca.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atividadelicenca-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atividadelicenca-ambiental-detail.edit', {
            parent: 'atividadelicenca-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadelicenca/atividadelicenca-ambiental-dialog.html',
                    controller: 'AtividadelicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Atividadelicenca', function(Atividadelicenca) {
                            return Atividadelicenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atividadelicenca-ambiental.new', {
            parent: 'atividadelicenca-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadelicenca/atividadelicenca-ambiental-dialog.html',
                    controller: 'AtividadelicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codigo: null,
                                descricao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('atividadelicenca-ambiental', null, { reload: 'atividadelicenca-ambiental' });
                }, function() {
                    $state.go('atividadelicenca-ambiental');
                });
            }]
        })
        .state('atividadelicenca-ambiental.edit', {
            parent: 'atividadelicenca-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadelicenca/atividadelicenca-ambiental-dialog.html',
                    controller: 'AtividadelicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Atividadelicenca', function(Atividadelicenca) {
                            return Atividadelicenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atividadelicenca-ambiental', null, { reload: 'atividadelicenca-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atividadelicenca-ambiental.delete', {
            parent: 'atividadelicenca-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividadelicenca/atividadelicenca-ambiental-delete-dialog.html',
                    controller: 'AtividadelicencaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Atividadelicenca', function(Atividadelicenca) {
                            return Atividadelicenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atividadelicenca-ambiental', null, { reload: 'atividadelicenca-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
