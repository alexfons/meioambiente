(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('edital-ambiental', {
            parent: 'entity',
            url: '/edital-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.edital.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/edital/editalsambiental.html',
                    controller: 'EditalAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('edital');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('edital-ambiental-detail', {
            parent: 'edital-ambiental',
            url: '/edital-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.edital.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/edital/edital-ambiental-detail.html',
                    controller: 'EditalAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('edital');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Edital', function($stateParams, Edital) {
                    return Edital.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'edital-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('edital-ambiental-detail.edit', {
            parent: 'edital-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/edital/edital-ambiental-dialog.html',
                    controller: 'EditalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Edital', function(Edital) {
                            return Edital.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('edital-ambiental.new', {
            parent: 'edital-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/edital/edital-ambiental-dialog.html',
                    controller: 'EditalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                comissaojulgamento: null,
                                dataaprovacao: null,
                                dataenvio: null,
                                datahoraabertura: null,
                                datalicitacao: null,
                                datanumeromanifestacao: null,
                                datapublicacao: null,
                                datarelatorio: null,
                                licitacao: null,
                                localrelatorio: null,
                                modalidade: null,
                                naturezaservico: null,
                                numeroconvite: null,
                                numeroedital: null,
                                numeromanifestacao: null,
                                numeroprojeto: null,
                                precoglobal: null,
                                tipoaquisicao: null,
                                tipolicitacao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('edital-ambiental', null, { reload: 'edital-ambiental' });
                }, function() {
                    $state.go('edital-ambiental');
                });
            }]
        })
        .state('edital-ambiental.edit', {
            parent: 'edital-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/edital/edital-ambiental-dialog.html',
                    controller: 'EditalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Edital', function(Edital) {
                            return Edital.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('edital-ambiental', null, { reload: 'edital-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('edital-ambiental.delete', {
            parent: 'edital-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/edital/edital-ambiental-delete-dialog.html',
                    controller: 'EditalAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Edital', function(Edital) {
                            return Edital.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('edital-ambiental', null, { reload: 'edital-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
