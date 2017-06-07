(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('empresa-ambiental', {
            parent: 'entity',
            url: '/empresa-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.empresa.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/empresa/empresasambiental.html',
                    controller: 'EmpresaAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('empresa');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('empresa-ambiental-detail', {
            parent: 'empresa-ambiental',
            url: '/empresa-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.empresa.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/empresa/empresa-ambiental-detail.html',
                    controller: 'EmpresaAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('empresa');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Empresa', function($stateParams, Empresa) {
                    return Empresa.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'empresa-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('empresa-ambiental-detail.edit', {
            parent: 'empresa-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresa/empresa-ambiental-dialog.html',
                    controller: 'EmpresaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Empresa', function(Empresa) {
                            return Empresa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('empresa-ambiental.new', {
            parent: 'empresa-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresa/empresa-ambiental-dialog.html',
                    controller: 'EmpresaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                bairro: null,
                                cidade: null,
                                cnpj: null,
                                contato: null,
                                email: null,
                                endereco: null,
                                idempresa: null,
                                nomeempresa: null,
                                numero: null,
                                pais: null,
                                telefonecontato: null,
                                uf: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('empresa-ambiental', null, { reload: 'empresa-ambiental' });
                }, function() {
                    $state.go('empresa-ambiental');
                });
            }]
        })
        .state('empresa-ambiental.edit', {
            parent: 'empresa-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresa/empresa-ambiental-dialog.html',
                    controller: 'EmpresaAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Empresa', function(Empresa) {
                            return Empresa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('empresa-ambiental', null, { reload: 'empresa-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('empresa-ambiental.delete', {
            parent: 'empresa-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/empresa/empresa-ambiental-delete-dialog.html',
                    controller: 'EmpresaAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Empresa', function(Empresa) {
                            return Empresa.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('empresa-ambiental', null, { reload: 'empresa-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
