(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipolicenca-ambiental', {
            parent: 'entity',
            url: '/tipolicenca-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipolicenca.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipolicenca/tipolicencasambiental.html',
                    controller: 'TipolicencaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipolicenca');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipolicenca-ambiental-detail', {
            parent: 'tipolicenca-ambiental',
            url: '/tipolicenca-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipolicenca.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipolicenca/tipolicenca-ambiental-detail.html',
                    controller: 'TipolicencaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipolicenca');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipolicenca', function($stateParams, Tipolicenca) {
                    return Tipolicenca.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipolicenca-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipolicenca-ambiental-detail.edit', {
            parent: 'tipolicenca-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipolicenca/tipolicenca-ambiental-dialog.html',
                    controller: 'TipolicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipolicenca', function(Tipolicenca) {
                            return Tipolicenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipolicenca-ambiental.new', {
            parent: 'tipolicenca-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipolicenca/tipolicenca-ambiental-dialog.html',
                    controller: 'TipolicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                categoria: null,
                                descricao: null,
                                subcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tipolicenca-ambiental', null, { reload: 'tipolicenca-ambiental' });
                }, function() {
                    $state.go('tipolicenca-ambiental');
                });
            }]
        })
        .state('tipolicenca-ambiental.edit', {
            parent: 'tipolicenca-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipolicenca/tipolicenca-ambiental-dialog.html',
                    controller: 'TipolicencaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipolicenca', function(Tipolicenca) {
                            return Tipolicenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipolicenca-ambiental', null, { reload: 'tipolicenca-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipolicenca-ambiental.delete', {
            parent: 'tipolicenca-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipolicenca/tipolicenca-ambiental-delete-dialog.html',
                    controller: 'TipolicencaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipolicenca', function(Tipolicenca) {
                            return Tipolicenca.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipolicenca-ambiental', null, { reload: 'tipolicenca-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
