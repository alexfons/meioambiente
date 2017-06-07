(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('informe-ambiental', {
            parent: 'entity',
            url: '/informe-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.informe.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/informe/informesambiental.html',
                    controller: 'InformeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('informe');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('informe-ambiental-detail', {
            parent: 'informe-ambiental',
            url: '/informe-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.informe.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/informe/informe-ambiental-detail.html',
                    controller: 'InformeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('informe');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Informe', function($stateParams, Informe) {
                    return Informe.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'informe-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('informe-ambiental-detail.edit', {
            parent: 'informe-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe/informe-ambiental-dialog.html',
                    controller: 'InformeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Informe', function(Informe) {
                            return Informe.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('informe-ambiental.new', {
            parent: 'informe-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe/informe-ambiental-dialog.html',
                    controller: 'InformeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                datainspecao: null,
                                notificacao: null,
                                numero: null,
                                obs: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('informe-ambiental', null, { reload: 'informe-ambiental' });
                }, function() {
                    $state.go('informe-ambiental');
                });
            }]
        })
        .state('informe-ambiental.edit', {
            parent: 'informe-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe/informe-ambiental-dialog.html',
                    controller: 'InformeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Informe', function(Informe) {
                            return Informe.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('informe-ambiental', null, { reload: 'informe-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('informe-ambiental.delete', {
            parent: 'informe-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe/informe-ambiental-delete-dialog.html',
                    controller: 'InformeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Informe', function(Informe) {
                            return Informe.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('informe-ambiental', null, { reload: 'informe-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
