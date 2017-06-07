(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipocertif-confor-ambiental', {
            parent: 'entity',
            url: '/tipocertif-confor-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipocertifConfor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipocertif-confor/tipocertif-conforsambiental.html',
                    controller: 'TipocertifConforAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipocertifConfor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipocertif-confor-ambiental-detail', {
            parent: 'tipocertif-confor-ambiental',
            url: '/tipocertif-confor-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipocertifConfor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipocertif-confor/tipocertif-confor-ambiental-detail.html',
                    controller: 'TipocertifConforAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipocertifConfor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'TipocertifConfor', function($stateParams, TipocertifConfor) {
                    return TipocertifConfor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipocertif-confor-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipocertif-confor-ambiental-detail.edit', {
            parent: 'tipocertif-confor-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertif-confor/tipocertif-confor-ambiental-dialog.html',
                    controller: 'TipocertifConforAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TipocertifConfor', function(TipocertifConfor) {
                            return TipocertifConfor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipocertif-confor-ambiental.new', {
            parent: 'tipocertif-confor-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertif-confor/tipocertif-confor-ambiental-dialog.html',
                    controller: 'TipocertifConforAmbientalDialogController',
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
                    $state.go('tipocertif-confor-ambiental', null, { reload: 'tipocertif-confor-ambiental' });
                }, function() {
                    $state.go('tipocertif-confor-ambiental');
                });
            }]
        })
        .state('tipocertif-confor-ambiental.edit', {
            parent: 'tipocertif-confor-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertif-confor/tipocertif-confor-ambiental-dialog.html',
                    controller: 'TipocertifConforAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['TipocertifConfor', function(TipocertifConfor) {
                            return TipocertifConfor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipocertif-confor-ambiental', null, { reload: 'tipocertif-confor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipocertif-confor-ambiental.delete', {
            parent: 'tipocertif-confor-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertif-confor/tipocertif-confor-ambiental-delete-dialog.html',
                    controller: 'TipocertifConforAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['TipocertifConfor', function(TipocertifConfor) {
                            return TipocertifConfor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipocertif-confor-ambiental', null, { reload: 'tipocertif-confor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
