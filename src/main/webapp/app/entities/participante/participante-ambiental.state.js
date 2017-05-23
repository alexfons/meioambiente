(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('participante-ambiental', {
            parent: 'entity',
            url: '/participante-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.participante.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/participante/participantesambiental.html',
                    controller: 'ParticipanteAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('participante');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('participante-ambiental-detail', {
            parent: 'participante-ambiental',
            url: '/participante-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.participante.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/participante/participante-ambiental-detail.html',
                    controller: 'ParticipanteAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('participante');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Participante', function($stateParams, Participante) {
                    return Participante.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'participante-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('participante-ambiental-detail.edit', {
            parent: 'participante-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/participante/participante-ambiental-dialog.html',
                    controller: 'ParticipanteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Participante', function(Participante) {
                            return Participante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('participante-ambiental.new', {
            parent: 'participante-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/participante/participante-ambiental-dialog.html',
                    controller: 'ParticipanteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('participante-ambiental', null, { reload: 'participante-ambiental' });
                }, function() {
                    $state.go('participante-ambiental');
                });
            }]
        })
        .state('participante-ambiental.edit', {
            parent: 'participante-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/participante/participante-ambiental-dialog.html',
                    controller: 'ParticipanteAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Participante', function(Participante) {
                            return Participante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('participante-ambiental', null, { reload: 'participante-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('participante-ambiental.delete', {
            parent: 'participante-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/participante/participante-ambiental-delete-dialog.html',
                    controller: 'ParticipanteAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Participante', function(Participante) {
                            return Participante.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('participante-ambiental', null, { reload: 'participante-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
