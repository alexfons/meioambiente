(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tipocertificadoconformidade-ambiental', {
            parent: 'entity',
            url: '/tipocertificadoconformidade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipocertificadoconformidade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipocertificadoconformidade/tipocertificadoconformidadesambiental.html',
                    controller: 'TipocertificadoconformidadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipocertificadoconformidade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tipocertificadoconformidade-ambiental-detail', {
            parent: 'tipocertificadoconformidade-ambiental',
            url: '/tipocertificadoconformidade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.tipocertificadoconformidade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tipocertificadoconformidade/tipocertificadoconformidade-ambiental-detail.html',
                    controller: 'TipocertificadoconformidadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tipocertificadoconformidade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tipocertificadoconformidade', function($stateParams, Tipocertificadoconformidade) {
                    return Tipocertificadoconformidade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tipocertificadoconformidade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tipocertificadoconformidade-ambiental-detail.edit', {
            parent: 'tipocertificadoconformidade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertificadoconformidade/tipocertificadoconformidade-ambiental-dialog.html',
                    controller: 'TipocertificadoconformidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipocertificadoconformidade', function(Tipocertificadoconformidade) {
                            return Tipocertificadoconformidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipocertificadoconformidade-ambiental.new', {
            parent: 'tipocertificadoconformidade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertificadoconformidade/tipocertificadoconformidade-ambiental-dialog.html',
                    controller: 'TipocertificadoconformidadeAmbientalDialogController',
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
                    $state.go('tipocertificadoconformidade-ambiental', null, { reload: 'tipocertificadoconformidade-ambiental' });
                }, function() {
                    $state.go('tipocertificadoconformidade-ambiental');
                });
            }]
        })
        .state('tipocertificadoconformidade-ambiental.edit', {
            parent: 'tipocertificadoconformidade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertificadoconformidade/tipocertificadoconformidade-ambiental-dialog.html',
                    controller: 'TipocertificadoconformidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tipocertificadoconformidade', function(Tipocertificadoconformidade) {
                            return Tipocertificadoconformidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipocertificadoconformidade-ambiental', null, { reload: 'tipocertificadoconformidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tipocertificadoconformidade-ambiental.delete', {
            parent: 'tipocertificadoconformidade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tipocertificadoconformidade/tipocertificadoconformidade-ambiental-delete-dialog.html',
                    controller: 'TipocertificadoconformidadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tipocertificadoconformidade', function(Tipocertificadoconformidade) {
                            return Tipocertificadoconformidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tipocertificadoconformidade-ambiental', null, { reload: 'tipocertificadoconformidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
