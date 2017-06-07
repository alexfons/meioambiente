(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('ocorrencia-certif-irreg-ambiental', {
            parent: 'entity',
            url: '/ocorrencia-certif-irreg-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciaCertifIrreg.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrencia-certif-irreg/ocorrencia-certif-irregsambiental.html',
                    controller: 'OcorrenciaCertifIrregAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciaCertifIrreg');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('ocorrencia-certif-irreg-ambiental-detail', {
            parent: 'ocorrencia-certif-irreg-ambiental',
            url: '/ocorrencia-certif-irreg-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.ocorrenciaCertifIrreg.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/ocorrencia-certif-irreg/ocorrencia-certif-irreg-ambiental-detail.html',
                    controller: 'OcorrenciaCertifIrregAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('ocorrenciaCertifIrreg');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'OcorrenciaCertifIrreg', function($stateParams, OcorrenciaCertifIrreg) {
                    return OcorrenciaCertifIrreg.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'ocorrencia-certif-irreg-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('ocorrencia-certif-irreg-ambiental-detail.edit', {
            parent: 'ocorrencia-certif-irreg-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia-certif-irreg/ocorrencia-certif-irreg-ambiental-dialog.html',
                    controller: 'OcorrenciaCertifIrregAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OcorrenciaCertifIrreg', function(OcorrenciaCertifIrreg) {
                            return OcorrenciaCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrencia-certif-irreg-ambiental.new', {
            parent: 'ocorrencia-certif-irreg-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia-certif-irreg/ocorrencia-certif-irreg-ambiental-dialog.html',
                    controller: 'OcorrenciaCertifIrregAmbientalDialogController',
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
                    $state.go('ocorrencia-certif-irreg-ambiental', null, { reload: 'ocorrencia-certif-irreg-ambiental' });
                }, function() {
                    $state.go('ocorrencia-certif-irreg-ambiental');
                });
            }]
        })
        .state('ocorrencia-certif-irreg-ambiental.edit', {
            parent: 'ocorrencia-certif-irreg-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia-certif-irreg/ocorrencia-certif-irreg-ambiental-dialog.html',
                    controller: 'OcorrenciaCertifIrregAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OcorrenciaCertifIrreg', function(OcorrenciaCertifIrreg) {
                            return OcorrenciaCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrencia-certif-irreg-ambiental', null, { reload: 'ocorrencia-certif-irreg-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('ocorrencia-certif-irreg-ambiental.delete', {
            parent: 'ocorrencia-certif-irreg-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/ocorrencia-certif-irreg/ocorrencia-certif-irreg-ambiental-delete-dialog.html',
                    controller: 'OcorrenciaCertifIrregAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OcorrenciaCertifIrreg', function(OcorrenciaCertifIrreg) {
                            return OcorrenciaCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('ocorrencia-certif-irreg-ambiental', null, { reload: 'ocorrencia-certif-irreg-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
