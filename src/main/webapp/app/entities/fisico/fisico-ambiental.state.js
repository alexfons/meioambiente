(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fisico-ambiental', {
            parent: 'entity',
            url: '/fisico-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fisico.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fisico/fisicosambiental.html',
                    controller: 'FisicoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fisico');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('fisico-ambiental-detail', {
            parent: 'fisico-ambiental',
            url: '/fisico-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fisico.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fisico/fisico-ambiental-detail.html',
                    controller: 'FisicoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fisico');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Fisico', function($stateParams, Fisico) {
                    return Fisico.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fisico-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fisico-ambiental-detail.edit', {
            parent: 'fisico-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisico/fisico-ambiental-dialog.html',
                    controller: 'FisicoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fisico', function(Fisico) {
                            return Fisico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fisico-ambiental.new', {
            parent: 'fisico-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisico/fisico-ambiental-dialog.html',
                    controller: 'FisicoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                extensao: null,
                                tipo: null,
                                tituloFim: null,
                                tituloInicio: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fisico-ambiental', null, { reload: 'fisico-ambiental' });
                }, function() {
                    $state.go('fisico-ambiental');
                });
            }]
        })
        .state('fisico-ambiental.edit', {
            parent: 'fisico-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisico/fisico-ambiental-dialog.html',
                    controller: 'FisicoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fisico', function(Fisico) {
                            return Fisico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fisico-ambiental', null, { reload: 'fisico-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fisico-ambiental.delete', {
            parent: 'fisico-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fisico/fisico-ambiental-delete-dialog.html',
                    controller: 'FisicoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fisico', function(Fisico) {
                            return Fisico.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fisico-ambiental', null, { reload: 'fisico-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
