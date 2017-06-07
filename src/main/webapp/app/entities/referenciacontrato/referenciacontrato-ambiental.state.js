(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('referenciacontrato-ambiental', {
            parent: 'entity',
            url: '/referenciacontrato-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.referenciacontrato.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/referenciacontrato/referenciacontratoesambiental.html',
                    controller: 'ReferenciacontratoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('referenciacontrato');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('referenciacontrato-ambiental-detail', {
            parent: 'referenciacontrato-ambiental',
            url: '/referenciacontrato-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.referenciacontrato.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/referenciacontrato/referenciacontrato-ambiental-detail.html',
                    controller: 'ReferenciacontratoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('referenciacontrato');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Referenciacontrato', function($stateParams, Referenciacontrato) {
                    return Referenciacontrato.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'referenciacontrato-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('referenciacontrato-ambiental-detail.edit', {
            parent: 'referenciacontrato-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referenciacontrato/referenciacontrato-ambiental-dialog.html',
                    controller: 'ReferenciacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Referenciacontrato', function(Referenciacontrato) {
                            return Referenciacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('referenciacontrato-ambiental.new', {
            parent: 'referenciacontrato-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referenciacontrato/referenciacontrato-ambiental-dialog.html',
                    controller: 'ReferenciacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                aporte: null,
                                moeda: null,
                                idreferenciacontrato: null,
                                nreferencia: null,
                                valorreferencia: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('referenciacontrato-ambiental', null, { reload: 'referenciacontrato-ambiental' });
                }, function() {
                    $state.go('referenciacontrato-ambiental');
                });
            }]
        })
        .state('referenciacontrato-ambiental.edit', {
            parent: 'referenciacontrato-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referenciacontrato/referenciacontrato-ambiental-dialog.html',
                    controller: 'ReferenciacontratoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Referenciacontrato', function(Referenciacontrato) {
                            return Referenciacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('referenciacontrato-ambiental', null, { reload: 'referenciacontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('referenciacontrato-ambiental.delete', {
            parent: 'referenciacontrato-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/referenciacontrato/referenciacontrato-ambiental-delete-dialog.html',
                    controller: 'ReferenciacontratoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Referenciacontrato', function(Referenciacontrato) {
                            return Referenciacontrato.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('referenciacontrato-ambiental', null, { reload: 'referenciacontrato-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
