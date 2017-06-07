(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('obraatividade-ambiental', {
            parent: 'entity',
            url: '/obraatividade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.obraatividade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/obraatividade/obraatividadesambiental.html',
                    controller: 'ObraatividadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('obraatividade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('obraatividade-ambiental-detail', {
            parent: 'obraatividade-ambiental',
            url: '/obraatividade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.obraatividade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/obraatividade/obraatividade-ambiental-detail.html',
                    controller: 'ObraatividadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('obraatividade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Obraatividade', function($stateParams, Obraatividade) {
                    return Obraatividade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'obraatividade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('obraatividade-ambiental-detail.edit', {
            parent: 'obraatividade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obraatividade/obraatividade-ambiental-dialog.html',
                    controller: 'ObraatividadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Obraatividade', function(Obraatividade) {
                            return Obraatividade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('obraatividade-ambiental.new', {
            parent: 'obraatividade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obraatividade/obraatividade-ambiental-dialog.html',
                    controller: 'ObraatividadeAmbientalDialogController',
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
                    $state.go('obraatividade-ambiental', null, { reload: 'obraatividade-ambiental' });
                }, function() {
                    $state.go('obraatividade-ambiental');
                });
            }]
        })
        .state('obraatividade-ambiental.edit', {
            parent: 'obraatividade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obraatividade/obraatividade-ambiental-dialog.html',
                    controller: 'ObraatividadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Obraatividade', function(Obraatividade) {
                            return Obraatividade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('obraatividade-ambiental', null, { reload: 'obraatividade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('obraatividade-ambiental.delete', {
            parent: 'obraatividade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/obraatividade/obraatividade-ambiental-delete-dialog.html',
                    controller: 'ObraatividadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Obraatividade', function(Obraatividade) {
                            return Obraatividade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('obraatividade-ambiental', null, { reload: 'obraatividade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
