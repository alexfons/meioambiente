(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('fiscal-ambiental', {
            parent: 'entity',
            url: '/fiscal-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fiscal.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fiscal/fiscalsambiental.html',
                    controller: 'FiscalAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fiscal');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('fiscal-ambiental-detail', {
            parent: 'fiscal-ambiental',
            url: '/fiscal-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.fiscal.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/fiscal/fiscal-ambiental-detail.html',
                    controller: 'FiscalAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('fiscal');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Fiscal', function($stateParams, Fiscal) {
                    return Fiscal.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'fiscal-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('fiscal-ambiental-detail.edit', {
            parent: 'fiscal-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fiscal/fiscal-ambiental-dialog.html',
                    controller: 'FiscalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fiscal', function(Fiscal) {
                            return Fiscal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fiscal-ambiental.new', {
            parent: 'fiscal-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fiscal/fiscal-ambiental-dialog.html',
                    controller: 'FiscalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                email: null,
                                funcao: null,
                                localtrabalho: null,
                                matricula: null,
                                nome: null,
                                superintendencia: null,
                                telefone: null,
                                telefonecomercial: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('fiscal-ambiental', null, { reload: 'fiscal-ambiental' });
                }, function() {
                    $state.go('fiscal-ambiental');
                });
            }]
        })
        .state('fiscal-ambiental.edit', {
            parent: 'fiscal-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fiscal/fiscal-ambiental-dialog.html',
                    controller: 'FiscalAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Fiscal', function(Fiscal) {
                            return Fiscal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fiscal-ambiental', null, { reload: 'fiscal-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('fiscal-ambiental.delete', {
            parent: 'fiscal-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/fiscal/fiscal-ambiental-delete-dialog.html',
                    controller: 'FiscalAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Fiscal', function(Fiscal) {
                            return Fiscal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('fiscal-ambiental', null, { reload: 'fiscal-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
