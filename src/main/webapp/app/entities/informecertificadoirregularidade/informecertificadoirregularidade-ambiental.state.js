(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('informecertificadoirregularidade-ambiental', {
            parent: 'entity',
            url: '/informecertificadoirregularidade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.informecertificadoirregularidade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/informecertificadoirregularidade/informecertificadoirregularidadesambiental.html',
                    controller: 'InformecertificadoirregularidadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('informecertificadoirregularidade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('informecertificadoirregularidade-ambiental-detail', {
            parent: 'informecertificadoirregularidade-ambiental',
            url: '/informecertificadoirregularidade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.informecertificadoirregularidade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/informecertificadoirregularidade/informecertificadoirregularidade-ambiental-detail.html',
                    controller: 'InformecertificadoirregularidadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('informecertificadoirregularidade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Informecertificadoirregularidade', function($stateParams, Informecertificadoirregularidade) {
                    return Informecertificadoirregularidade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'informecertificadoirregularidade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('informecertificadoirregularidade-ambiental-detail.edit', {
            parent: 'informecertificadoirregularidade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informecertificadoirregularidade/informecertificadoirregularidade-ambiental-dialog.html',
                    controller: 'InformecertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Informecertificadoirregularidade', function(Informecertificadoirregularidade) {
                            return Informecertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('informecertificadoirregularidade-ambiental.new', {
            parent: 'informecertificadoirregularidade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informecertificadoirregularidade/informecertificadoirregularidade-ambiental-dialog.html',
                    controller: 'InformecertificadoirregularidadeAmbientalDialogController',
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
                    $state.go('informecertificadoirregularidade-ambiental', null, { reload: 'informecertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('informecertificadoirregularidade-ambiental');
                });
            }]
        })
        .state('informecertificadoirregularidade-ambiental.edit', {
            parent: 'informecertificadoirregularidade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informecertificadoirregularidade/informecertificadoirregularidade-ambiental-dialog.html',
                    controller: 'InformecertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Informecertificadoirregularidade', function(Informecertificadoirregularidade) {
                            return Informecertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('informecertificadoirregularidade-ambiental', null, { reload: 'informecertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('informecertificadoirregularidade-ambiental.delete', {
            parent: 'informecertificadoirregularidade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informecertificadoirregularidade/informecertificadoirregularidade-ambiental-delete-dialog.html',
                    controller: 'InformecertificadoirregularidadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Informecertificadoirregularidade', function(Informecertificadoirregularidade) {
                            return Informecertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('informecertificadoirregularidade-ambiental', null, { reload: 'informecertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
