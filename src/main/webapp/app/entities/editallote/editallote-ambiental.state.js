(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('editallote-ambiental', {
            parent: 'entity',
            url: '/editallote-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.editallote.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/editallote/editallotesambiental.html',
                    controller: 'EditalloteAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('editallote');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('editallote-ambiental-detail', {
            parent: 'editallote-ambiental',
            url: '/editallote-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.editallote.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/editallote/editallote-ambiental-detail.html',
                    controller: 'EditalloteAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('editallote');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Editallote', function($stateParams, Editallote) {
                    return Editallote.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'editallote-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('editallote-ambiental-detail.edit', {
            parent: 'editallote-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editallote/editallote-ambiental-dialog.html',
                    controller: 'EditalloteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Editallote', function(Editallote) {
                            return Editallote.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('editallote-ambiental.new', {
            parent: 'editallote-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editallote/editallote-ambiental-dialog.html',
                    controller: 'EditalloteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                dataprovacaolote: null,
                                datarelatoriolote: null,
                                idlote: null,
                                lote: null,
                                objeto: null,
                                prazo: null,
                                valororcado: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('editallote-ambiental', null, { reload: 'editallote-ambiental' });
                }, function() {
                    $state.go('editallote-ambiental');
                });
            }]
        })
        .state('editallote-ambiental.edit', {
            parent: 'editallote-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editallote/editallote-ambiental-dialog.html',
                    controller: 'EditalloteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Editallote', function(Editallote) {
                            return Editallote.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('editallote-ambiental', null, { reload: 'editallote-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('editallote-ambiental.delete', {
            parent: 'editallote-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/editallote/editallote-ambiental-delete-dialog.html',
                    controller: 'EditalloteAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Editallote', function(Editallote) {
                            return Editallote.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('editallote-ambiental', null, { reload: 'editallote-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
