(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('custosconcorrentes-ambiental', {
            parent: 'entity',
            url: '/custosconcorrentes-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.custosconcorrentes.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/custosconcorrentes/custosconcorrentesambiental.html',
                    controller: 'CustosconcorrentesAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('custosconcorrentes');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('custosconcorrentes-ambiental-detail', {
            parent: 'custosconcorrentes-ambiental',
            url: '/custosconcorrentes-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.custosconcorrentes.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/custosconcorrentes/custosconcorrentes-ambiental-detail.html',
                    controller: 'CustosconcorrentesAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('custosconcorrentes');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Custosconcorrentes', function($stateParams, Custosconcorrentes) {
                    return Custosconcorrentes.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'custosconcorrentes-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('custosconcorrentes-ambiental-detail.edit', {
            parent: 'custosconcorrentes-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/custosconcorrentes/custosconcorrentes-ambiental-dialog.html',
                    controller: 'CustosconcorrentesAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Custosconcorrentes', function(Custosconcorrentes) {
                            return Custosconcorrentes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('custosconcorrentes-ambiental.new', {
            parent: 'custosconcorrentes-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/custosconcorrentes/custosconcorrentes-ambiental-dialog.html',
                    controller: 'CustosconcorrentesAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                datainicio: null,
                                nsolicitacao: null,
                                valorus: null,
                                valorpagoreais: null,
                                taxa: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('custosconcorrentes-ambiental', null, { reload: 'custosconcorrentes-ambiental' });
                }, function() {
                    $state.go('custosconcorrentes-ambiental');
                });
            }]
        })
        .state('custosconcorrentes-ambiental.edit', {
            parent: 'custosconcorrentes-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/custosconcorrentes/custosconcorrentes-ambiental-dialog.html',
                    controller: 'CustosconcorrentesAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Custosconcorrentes', function(Custosconcorrentes) {
                            return Custosconcorrentes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('custosconcorrentes-ambiental', null, { reload: 'custosconcorrentes-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('custosconcorrentes-ambiental.delete', {
            parent: 'custosconcorrentes-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/custosconcorrentes/custosconcorrentes-ambiental-delete-dialog.html',
                    controller: 'CustosconcorrentesAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Custosconcorrentes', function(Custosconcorrentes) {
                            return Custosconcorrentes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('custosconcorrentes-ambiental', null, { reload: 'custosconcorrentes-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
