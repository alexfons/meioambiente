(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('categoriainversao-ambiental', {
            parent: 'entity',
            url: '/categoriainversao-ambiental',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.categoriainversao.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categoriainversao/categoriainversaosambiental.html',
                    controller: 'CategoriainversaoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('categoriainversao');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('categoriainversao-ambiental-detail', {
            parent: 'categoriainversao-ambiental',
            url: '/categoriainversao-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.categoriainversao.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/categoriainversao/categoriainversao-ambiental-detail.html',
                    controller: 'CategoriainversaoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('categoriainversao');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Categoriainversao', function($stateParams, Categoriainversao) {
                    return Categoriainversao.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'categoriainversao-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('categoriainversao-ambiental-detail.edit', {
            parent: 'categoriainversao-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoriainversao/categoriainversao-ambiental-dialog.html',
                    controller: 'CategoriainversaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categoriainversao', function(Categoriainversao) {
                            return Categoriainversao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoriainversao-ambiental.new', {
            parent: 'categoriainversao-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoriainversao/categoriainversao-ambiental-dialog.html',
                    controller: 'CategoriainversaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                codcategoria: null,
                                descricaocategoria: null,
                                descricaoitem: null,
                                descricaosubcategoria: null,
                                descricaosubitem: null,
                                idcategoriainversao: null,
                                item: null,
                                percentualcatagente: null,
                                percentualcatlocal: null,
                                subcategoria: null,
                                subitem: null,
                                valorcatagente: null,
                                valorcatlocal: null,
                                valorporcategoria: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('categoriainversao-ambiental', null, { reload: 'categoriainversao-ambiental' });
                }, function() {
                    $state.go('categoriainversao-ambiental');
                });
            }]
        })
        .state('categoriainversao-ambiental.edit', {
            parent: 'categoriainversao-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoriainversao/categoriainversao-ambiental-dialog.html',
                    controller: 'CategoriainversaoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Categoriainversao', function(Categoriainversao) {
                            return Categoriainversao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoriainversao-ambiental', null, { reload: 'categoriainversao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('categoriainversao-ambiental.delete', {
            parent: 'categoriainversao-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/categoriainversao/categoriainversao-ambiental-delete-dialog.html',
                    controller: 'CategoriainversaoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Categoriainversao', function(Categoriainversao) {
                            return Categoriainversao.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('categoriainversao-ambiental', null, { reload: 'categoriainversao-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
