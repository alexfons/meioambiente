(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('natureza-ambiental', {
            parent: 'entity',
            url: '/natureza-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.natureza.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/natureza/naturezasambiental.html',
                    controller: 'NaturezaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('natureza');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('natureza-ambiental-detail', {
            parent: 'natureza-ambiental',
            url: '/natureza-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.natureza.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/natureza/natureza-ambiental-detail.html',
                    controller: 'NaturezaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('natureza');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Natureza', function($stateParams, Natureza) {
                    return Natureza.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'natureza-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('natureza-ambiental-detail.edit', {
            parent: 'natureza-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/natureza/natureza-ambiental-dialog.html',
                    controller: 'NaturezaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Natureza', function(Natureza) {
                            return Natureza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('natureza-ambiental.new', {
            parent: 'natureza-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/natureza/natureza-ambiental-dialog.html',
                    controller: 'NaturezaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descnatureza: null,
                                descsubacao: null,
                                idnatureza: null,
                                numnatureza: null,
                                subacao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('natureza-ambiental', null, { reload: 'natureza-ambiental' });
                }, function() {
                    $state.go('natureza-ambiental');
                });
            }]
        })
        .state('natureza-ambiental.edit', {
            parent: 'natureza-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/natureza/natureza-ambiental-dialog.html',
                    controller: 'NaturezaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Natureza', function(Natureza) {
                            return Natureza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('natureza-ambiental', null, { reload: 'natureza-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('natureza-ambiental.delete', {
            parent: 'natureza-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/natureza/natureza-ambiental-delete-dialog.html',
                    controller: 'NaturezaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Natureza', function(Natureza) {
                            return Natureza.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('natureza-ambiental', null, { reload: 'natureza-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
