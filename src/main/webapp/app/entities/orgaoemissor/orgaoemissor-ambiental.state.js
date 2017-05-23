(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('orgaoemissor-ambiental', {
            parent: 'entity',
            url: '/orgaoemissor-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.orgaoemissor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/orgaoemissor/orgaoemissorsambiental.html',
                    controller: 'OrgaoemissorAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('orgaoemissor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('orgaoemissor-ambiental-detail', {
            parent: 'orgaoemissor-ambiental',
            url: '/orgaoemissor-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.orgaoemissor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/orgaoemissor/orgaoemissor-ambiental-detail.html',
                    controller: 'OrgaoemissorAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('orgaoemissor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Orgaoemissor', function($stateParams, Orgaoemissor) {
                    return Orgaoemissor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'orgaoemissor-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('orgaoemissor-ambiental-detail.edit', {
            parent: 'orgaoemissor-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orgaoemissor/orgaoemissor-ambiental-dialog.html',
                    controller: 'OrgaoemissorAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Orgaoemissor', function(Orgaoemissor) {
                            return Orgaoemissor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('orgaoemissor-ambiental.new', {
            parent: 'orgaoemissor-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orgaoemissor/orgaoemissor-ambiental-dialog.html',
                    controller: 'OrgaoemissorAmbientalDialogController',
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
                    $state.go('orgaoemissor-ambiental', null, { reload: 'orgaoemissor-ambiental' });
                }, function() {
                    $state.go('orgaoemissor-ambiental');
                });
            }]
        })
        .state('orgaoemissor-ambiental.edit', {
            parent: 'orgaoemissor-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orgaoemissor/orgaoemissor-ambiental-dialog.html',
                    controller: 'OrgaoemissorAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Orgaoemissor', function(Orgaoemissor) {
                            return Orgaoemissor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('orgaoemissor-ambiental', null, { reload: 'orgaoemissor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('orgaoemissor-ambiental.delete', {
            parent: 'orgaoemissor-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/orgaoemissor/orgaoemissor-ambiental-delete-dialog.html',
                    controller: 'OrgaoemissorAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Orgaoemissor', function(Orgaoemissor) {
                            return Orgaoemissor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('orgaoemissor-ambiental', null, { reload: 'orgaoemissor-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
