(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('clausulascontratuais-ambiental', {
            parent: 'entity',
            url: '/clausulascontratuais-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.clausulascontratuais.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/clausulascontratuais/clausulascontratuaisambiental.html',
                    controller: 'ClausulascontratuaisAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clausulascontratuais');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('clausulascontratuais-ambiental-detail', {
            parent: 'clausulascontratuais-ambiental',
            url: '/clausulascontratuais-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.clausulascontratuais.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/clausulascontratuais/clausulascontratuais-ambiental-detail.html',
                    controller: 'ClausulascontratuaisAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('clausulascontratuais');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Clausulascontratuais', function($stateParams, Clausulascontratuais) {
                    return Clausulascontratuais.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'clausulascontratuais-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('clausulascontratuais-ambiental-detail.edit', {
            parent: 'clausulascontratuais-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/clausulascontratuais/clausulascontratuais-ambiental-dialog.html',
                    controller: 'ClausulascontratuaisAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Clausulascontratuais', function(Clausulascontratuais) {
                            return Clausulascontratuais.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('clausulascontratuais-ambiental.new', {
            parent: 'clausulascontratuais-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/clausulascontratuais/clausulascontratuais-ambiental-dialog.html',
                    controller: 'ClausulascontratuaisAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                artigo: null,
                                descricao: null,
                                noficioenviado: null,
                                noficioaprovado: null,
                                dataaprovacao: null,
                                dataenvio: null,
                                datavigente: null,
                                idclausulascontratuais: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('clausulascontratuais-ambiental', null, { reload: 'clausulascontratuais-ambiental' });
                }, function() {
                    $state.go('clausulascontratuais-ambiental');
                });
            }]
        })
        .state('clausulascontratuais-ambiental.edit', {
            parent: 'clausulascontratuais-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/clausulascontratuais/clausulascontratuais-ambiental-dialog.html',
                    controller: 'ClausulascontratuaisAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Clausulascontratuais', function(Clausulascontratuais) {
                            return Clausulascontratuais.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('clausulascontratuais-ambiental', null, { reload: 'clausulascontratuais-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('clausulascontratuais-ambiental.delete', {
            parent: 'clausulascontratuais-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/clausulascontratuais/clausulascontratuais-ambiental-delete-dialog.html',
                    controller: 'ClausulascontratuaisAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Clausulascontratuais', function(Clausulascontratuais) {
                            return Clausulascontratuais.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('clausulascontratuais-ambiental', null, { reload: 'clausulascontratuais-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
