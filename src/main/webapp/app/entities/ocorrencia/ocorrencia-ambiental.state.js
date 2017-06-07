(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrencia-ambiental', {
            parent: 'entity',
            url: '/ocorrencia-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrencia.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrencia/ocorrenciasambiental.html',
                    controller: 'OcorrenciaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrencia');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrencia-ambiental-detail', {
            parent: 'ocorrencia-ambiental',
            url: '/ocorrencia-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrencia.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrencia/ocorrencia-ambiental-detail.html',
                    controller: 'OcorrenciaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrencia');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Ocorrencia', function($stateParams, Ocorrencia) {
                    return Ocorrencia.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrencia-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrencia-ambiental-detail.edit', {
            parent: 'ocorrencia-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia/ocorrencia-ambiental-dialog.html',
                    controller: 'OcorrenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrencia', function(Ocorrencia) {
                            return Ocorrencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrencia-ambiental.new', {
            parent: 'ocorrencia-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia/ocorrencia-ambiental-dialog.html',
                    controller: 'OcorrenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                album: null,
                                caracterizacao: null,
                                coordenadaa: null,
                                coordenadae: null,
                                coordenadan: null,
                                data: null,
                                distanciaeixo: null,
                                feature: null,
                                kmfim: null,
                                kminicio: null,
                                lado: null,
                                numero: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('ocorrencia-ambiental', null, { reload: 'ocorrencia-ambiental' });
                }, function() {
                    $state.go('ocorrencia-ambiental');
                });
            }]
        })
        .state('ocorrencia-ambiental.edit', {
            parent: 'ocorrencia-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia/ocorrencia-ambiental-dialog.html',
                    controller: 'OcorrenciaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Ocorrencia', function(Ocorrencia) {
                            return Ocorrencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrencia-ambiental', null, { reload: 'ocorrencia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrencia-ambiental.delete', {
            parent: 'ocorrencia-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia/ocorrencia-ambiental-delete-dialog.html',
                    controller: 'OcorrenciaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Ocorrencia', function(Ocorrencia) {
                            return Ocorrencia.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrencia-ambiental', null, { reload: 'ocorrencia-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
