(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('atividade-ambiental', {
            parent: 'entity',
            url: '/atividade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.atividade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atividade/atividadesambiental.html',
                    controller: 'AtividadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('atividade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('atividade-ambiental-detail', {
            parent: 'atividade-ambiental',
            url: '/atividade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.atividade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/atividade/atividade-ambiental-detail.html',
                    controller: 'AtividadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('atividade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Atividade', function($stateParams, Atividade) {
                    return Atividade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'atividade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('atividade-ambiental-detail.edit', {
            parent: 'atividade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividade/atividade-ambiental-dialog.html',
                    controller: 'AtividadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Atividade', function(Atividade) {
                            return Atividade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atividade-ambiental.new', {
            parent: 'atividade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividade/atividade-ambiental-dialog.html',
                    controller: 'AtividadeAmbientalDialogController',
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
                    $state.go('atividade-ambiental', null, { reload: 'atividade-ambiental' });
                }, function() {
                    $state.go('atividade-ambiental');
                });
            }]
        })
        .state('atividade-ambiental.edit', {
            parent: 'atividade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividade/atividade-ambiental-dialog.html',
                    controller: 'AtividadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Atividade', function(Atividade) {
                            return Atividade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atividade-ambiental', null, { reload: 'atividade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('atividade-ambiental.delete', {
            parent: 'atividade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/atividade/atividade-ambiental-delete-dialog.html',
                    controller: 'AtividadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Atividade', function(Atividade) {
                            return Atividade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('atividade-ambiental', null, { reload: 'atividade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
