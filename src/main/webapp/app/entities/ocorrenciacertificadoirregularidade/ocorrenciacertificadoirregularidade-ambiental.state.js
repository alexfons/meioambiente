(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrenciacertificadoirregularidade-ambiental', {
            parent: 'entity',
            url: '/ocorrenciacertificadoirregularidade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciacertificadoirregularidade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciacertificadoirregularidade/ocorrenciacertificadoirregularidadesambiental.html',
                    controller: 'OcorrenciacertificadoirregularidadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciacertificadoirregularidade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrenciacertificadoirregularidade-ambiental-detail', {
            parent: 'ocorrenciacertificadoirregularidade-ambiental',
            url: '/ocorrenciacertificadoirregularidade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciacertificadoirregularidade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrenciacertificadoirregularidade/ocorrenciacertificadoirregularidade-ambiental-detail.html',
                    controller: 'OcorrenciacertificadoirregularidadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciacertificadoirregularidade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ocorrenciacertificadoirregularidade', function($stateParams, Ocorrenciacertificadoirregularidade) {
                    return Ocorrenciacertificadoirregularidade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrenciacertificadoirregularidade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrenciacertificadoirregularidade-ambiental-detail.edit', {
            parent: 'ocorrenciacertificadoirregularidade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciacertificadoirregularidade/ocorrenciacertificadoirregularidade-ambiental-dialog.html',
                    controller: 'OcorrenciacertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciacertificadoirregularidade', function(Ocorrenciacertificadoirregularidade) {
                            return Ocorrenciacertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciacertificadoirregularidade-ambiental.new', {
            parent: 'ocorrenciacertificadoirregularidade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciacertificadoirregularidade/ocorrenciacertificadoirregularidade-ambiental-dialog.html',
                    controller: 'OcorrenciacertificadoirregularidadeAmbientalDialogController',
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
                    $state.go('ocorrenciacertificadoirregularidade-ambiental', null, { reload: 'ocorrenciacertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('ocorrenciacertificadoirregularidade-ambiental');
                });
            }]
        })
        .state('ocorrenciacertificadoirregularidade-ambiental.edit', {
            parent: 'ocorrenciacertificadoirregularidade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciacertificadoirregularidade/ocorrenciacertificadoirregularidade-ambiental-dialog.html',
                    controller: 'OcorrenciacertificadoirregularidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrenciacertificadoirregularidade', function(Ocorrenciacertificadoirregularidade) {
                            return Ocorrenciacertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciacertificadoirregularidade-ambiental', null, { reload: 'ocorrenciacertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrenciacertificadoirregularidade-ambiental.delete', {
            parent: 'ocorrenciacertificadoirregularidade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrenciacertificadoirregularidade/ocorrenciacertificadoirregularidade-ambiental-delete-dialog.html',
                    controller: 'OcorrenciacertificadoirregularidadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ocorrenciacertificadoirregularidade', function(Ocorrenciacertificadoirregularidade) {
                            return Ocorrenciacertificadoirregularidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrenciacertificadoirregularidade-ambiental', null, { reload: 'ocorrenciacertificadoirregularidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
