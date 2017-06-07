(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('quantitativo-ambiental', {
            parent: 'entity',
            url: '/quantitativo-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.quantitativo.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/quantitativo/quantitativosambiental.html',
                    controller: 'QuantitativoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('quantitativo');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('quantitativo-ambiental-detail', {
            parent: 'quantitativo-ambiental',
            url: '/quantitativo-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.quantitativo.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/quantitativo/quantitativo-ambiental-detail.html',
                    controller: 'QuantitativoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('quantitativo');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Quantitativo', function($stateParams, Quantitativo) {
                    return Quantitativo.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'quantitativo-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('quantitativo-ambiental-detail.edit', {
            parent: 'quantitativo-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quantitativo/quantitativo-ambiental-dialog.html',
                    controller: 'QuantitativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Quantitativo', function(Quantitativo) {
                            return Quantitativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('quantitativo-ambiental.new', {
            parent: 'quantitativo-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quantitativo/quantitativo-ambiental-dialog.html',
                    controller: 'QuantitativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descricao: null,
                                idquantitativo: null,
                                quantidade: null,
                                total: null,
                                unidade: null,
                                unitario: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('quantitativo-ambiental', null, { reload: 'quantitativo-ambiental' });
                }, function() {
                    $state.go('quantitativo-ambiental');
                });
            }]
        })
        .state('quantitativo-ambiental.edit', {
            parent: 'quantitativo-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quantitativo/quantitativo-ambiental-dialog.html',
                    controller: 'QuantitativoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Quantitativo', function(Quantitativo) {
                            return Quantitativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('quantitativo-ambiental', null, { reload: 'quantitativo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('quantitativo-ambiental.delete', {
            parent: 'quantitativo-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/quantitativo/quantitativo-ambiental-delete-dialog.html',
                    controller: 'QuantitativoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Quantitativo', function(Quantitativo) {
                            return Quantitativo.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('quantitativo-ambiental', null, { reload: 'quantitativo-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
