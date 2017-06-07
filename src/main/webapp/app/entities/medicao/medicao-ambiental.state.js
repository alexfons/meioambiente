(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('medicao-ambiental', {
            parent: 'entity',
            url: '/medicao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.medicao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/medicao/medicaosambiental.html',
                    controller: 'MedicaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('medicao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('medicao-ambiental-detail', {
            parent: 'medicao-ambiental',
            url: '/medicao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.medicao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/medicao/medicao-ambiental-detail.html',
                    controller: 'MedicaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('medicao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Medicao', function($stateParams, Medicao) {
                    return Medicao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'medicao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('medicao-ambiental-detail.edit', {
            parent: 'medicao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medicao/medicao-ambiental-dialog.html',
                    controller: 'MedicaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Medicao', function(Medicao) {
                            return Medicao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('medicao-ambiental.new', {
            parent: 'medicao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medicao/medicao-ambiental-dialog.html',
                    controller: 'MedicaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                ajustecambio: null,
                                conferido: null,
                                idmedicao: null,
                                mes: null,
                                nummedicao: null,
                                tipomedicao: null,
                                valormedicao: null,
                                valormedicaoreajuste: null,
                                valorusmedicao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('medicao-ambiental', null, { reload: 'medicao-ambiental' });
                }, function() {
                    $state.go('medicao-ambiental');
                });
            }]
        })
        .state('medicao-ambiental.edit', {
            parent: 'medicao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medicao/medicao-ambiental-dialog.html',
                    controller: 'MedicaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Medicao', function(Medicao) {
                            return Medicao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('medicao-ambiental', null, { reload: 'medicao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('medicao-ambiental.delete', {
            parent: 'medicao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/medicao/medicao-ambiental-delete-dialog.html',
                    controller: 'MedicaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Medicao', function(Medicao) {
                            return Medicao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('medicao-ambiental', null, { reload: 'medicao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
