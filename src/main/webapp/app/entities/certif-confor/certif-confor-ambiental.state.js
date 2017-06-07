(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('certif-confor-ambiental', {
            parent: 'entity',
            url: '/certif-confor-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.certifConfor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/certif-confor/certif-conforsambiental.html',
                    controller: 'CertifConforAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('certifConfor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('certif-confor-ambiental-detail', {
            parent: 'certif-confor-ambiental',
            url: '/certif-confor-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.certifConfor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/certif-confor/certif-confor-ambiental-detail.html',
                    controller: 'CertifConforAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('certifConfor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'CertifConfor', function($stateParams, CertifConfor) {
                    return CertifConfor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'certif-confor-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('certif-confor-ambiental-detail.edit', {
            parent: 'certif-confor-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certif-confor/certif-confor-ambiental-dialog.html',
                    controller: 'CertifConforAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CertifConfor', function(CertifConfor) {
                            return CertifConfor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('certif-confor-ambiental.new', {
            parent: 'certif-confor-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certif-confor/certif-confor-ambiental-dialog.html',
                    controller: 'CertifConforAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                data: null,
                                dataliberacao: null,
                                dataparalisacao: null,
                                datareinicio: null,
                                edital: null,
                                item: null,
                                liberacao: null,
                                liberacaoadministrativamente: null,
                                periodo: null,
                                texto: null,
                                texto2: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('certif-confor-ambiental', null, { reload: 'certif-confor-ambiental' });
                }, function() {
                    $state.go('certif-confor-ambiental');
                });
            }]
        })
        .state('certif-confor-ambiental.edit', {
            parent: 'certif-confor-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certif-confor/certif-confor-ambiental-dialog.html',
                    controller: 'CertifConforAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['CertifConfor', function(CertifConfor) {
                            return CertifConfor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('certif-confor-ambiental', null, { reload: 'certif-confor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('certif-confor-ambiental.delete', {
            parent: 'certif-confor-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certif-confor/certif-confor-ambiental-delete-dialog.html',
                    controller: 'CertifConforAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['CertifConfor', function(CertifConfor) {
                            return CertifConfor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('certif-confor-ambiental', null, { reload: 'certif-confor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
