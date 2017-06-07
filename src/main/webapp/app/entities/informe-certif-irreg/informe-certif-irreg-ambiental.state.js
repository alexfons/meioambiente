(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('informe-certif-irreg-ambiental', {
            parent: 'entity',
            url: '/informe-certif-irreg-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.informeCertifIrreg.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/informe-certif-irreg/informe-certif-irregsambiental.html',
                    controller: 'InformeCertifIrregAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('informeCertifIrreg');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('informe-certif-irreg-ambiental-detail', {
            parent: 'informe-certif-irreg-ambiental',
            url: '/informe-certif-irreg-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.informeCertifIrreg.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/informe-certif-irreg/informe-certif-irreg-ambiental-detail.html',
                    controller: 'InformeCertifIrregAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('informeCertifIrreg');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'InformeCertifIrreg', function($stateParams, InformeCertifIrreg) {
                    return InformeCertifIrreg.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'informe-certif-irreg-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('informe-certif-irreg-ambiental-detail.edit', {
            parent: 'informe-certif-irreg-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe-certif-irreg/informe-certif-irreg-ambiental-dialog.html',
                    controller: 'InformeCertifIrregAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InformeCertifIrreg', function(InformeCertifIrreg) {
                            return InformeCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('informe-certif-irreg-ambiental.new', {
            parent: 'informe-certif-irreg-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe-certif-irreg/informe-certif-irreg-ambiental-dialog.html',
                    controller: 'InformeCertifIrregAmbientalDialogController',
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
                    $state.go('informe-certif-irreg-ambiental', null, { reload: 'informe-certif-irreg-ambiental' });
                }, function() {
                    $state.go('informe-certif-irreg-ambiental');
                });
            }]
        })
        .state('informe-certif-irreg-ambiental.edit', {
            parent: 'informe-certif-irreg-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe-certif-irreg/informe-certif-irreg-ambiental-dialog.html',
                    controller: 'InformeCertifIrregAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['InformeCertifIrreg', function(InformeCertifIrreg) {
                            return InformeCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('informe-certif-irreg-ambiental', null, { reload: 'informe-certif-irreg-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('informe-certif-irreg-ambiental.delete', {
            parent: 'informe-certif-irreg-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/informe-certif-irreg/informe-certif-irreg-ambiental-delete-dialog.html',
                    controller: 'InformeCertifIrregAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['InformeCertifIrreg', function(InformeCertifIrreg) {
                            return InformeCertifIrreg.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('informe-certif-irreg-ambiental', null, { reload: 'informe-certif-irreg-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
