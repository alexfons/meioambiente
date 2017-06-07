(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('certificadoconformidade-ambiental', {
            parent: 'entity',
            url: '/certificadoconformidade-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.certificadoconformidade.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/certificadoconformidade/certificadoconformidadesambiental.html',
                    controller: 'CertificadoconformidadeAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('certificadoconformidade');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('certificadoconformidade-ambiental-detail', {
            parent: 'certificadoconformidade-ambiental',
            url: '/certificadoconformidade-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.certificadoconformidade.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/certificadoconformidade/certificadoconformidade-ambiental-detail.html',
                    controller: 'CertificadoconformidadeAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('certificadoconformidade');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Certificadoconformidade', function($stateParams, Certificadoconformidade) {
                    return Certificadoconformidade.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'certificadoconformidade-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('certificadoconformidade-ambiental-detail.edit', {
            parent: 'certificadoconformidade-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certificadoconformidade/certificadoconformidade-ambiental-dialog.html',
                    controller: 'CertificadoconformidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Certificadoconformidade', function(Certificadoconformidade) {
                            return Certificadoconformidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('certificadoconformidade-ambiental.new', {
            parent: 'certificadoconformidade-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certificadoconformidade/certificadoconformidade-ambiental-dialog.html',
                    controller: 'CertificadoconformidadeAmbientalDialogController',
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
                    $state.go('certificadoconformidade-ambiental', null, { reload: 'certificadoconformidade-ambiental' });
                }, function() {
                    $state.go('certificadoconformidade-ambiental');
                });
            }]
        })
        .state('certificadoconformidade-ambiental.edit', {
            parent: 'certificadoconformidade-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certificadoconformidade/certificadoconformidade-ambiental-dialog.html',
                    controller: 'CertificadoconformidadeAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Certificadoconformidade', function(Certificadoconformidade) {
                            return Certificadoconformidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('certificadoconformidade-ambiental', null, { reload: 'certificadoconformidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('certificadoconformidade-ambiental.delete', {
            parent: 'certificadoconformidade-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/certificadoconformidade/certificadoconformidade-ambiental-delete-dialog.html',
                    controller: 'CertificadoconformidadeAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Certificadoconformidade', function(Certificadoconformidade) {
                            return Certificadoconformidade.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('certificadoconformidade-ambiental', null, { reload: 'certificadoconformidade-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
