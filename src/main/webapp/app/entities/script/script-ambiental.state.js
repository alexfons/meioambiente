(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('script-ambiental', {
            parent: 'entity',
            url: '/script-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.script.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/script/scriptsambiental.html',
                    controller: 'ScriptAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('script');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('script-ambiental-detail', {
            parent: 'script-ambiental',
            url: '/script-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.script.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/script/script-ambiental-detail.html',
                    controller: 'ScriptAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('script');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Script', function($stateParams, Script) {
                    return Script.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'script-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('script-ambiental-detail.edit', {
            parent: 'script-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/script/script-ambiental-dialog.html',
                    controller: 'ScriptAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Script', function(Script) {
                            return Script.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('script-ambiental.new', {
            parent: 'script-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/script/script-ambiental-dialog.html',
                    controller: 'ScriptAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codigo: null,
                                dataVerificacaoLicencas: null,
                                descricao: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('script-ambiental', null, { reload: 'script-ambiental' });
                }, function() {
                    $state.go('script-ambiental');
                });
            }]
        })
        .state('script-ambiental.edit', {
            parent: 'script-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/script/script-ambiental-dialog.html',
                    controller: 'ScriptAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Script', function(Script) {
                            return Script.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('script-ambiental', null, { reload: 'script-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('script-ambiental.delete', {
            parent: 'script-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/script/script-ambiental-delete-dialog.html',
                    controller: 'ScriptAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Script', function(Script) {
                            return Script.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('script-ambiental', null, { reload: 'script-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
