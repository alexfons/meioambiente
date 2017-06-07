(function() {
    'use strict';

    angular
        .module('meioambienteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('foto-ambiental', {
            parent: 'entity',
            url: '/foto-ambiental?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.foto.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/foto/fotosambiental.html',
                    controller: 'FotoAmbientalController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('foto');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('foto-ambiental-detail', {
            parent: 'foto-ambiental',
            url: '/foto-ambiental/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'meioambienteApp.foto.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/foto/foto-ambiental-detail.html',
                    controller: 'FotoAmbientalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('foto');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Foto', function($stateParams, Foto) {
                    return Foto.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'foto-ambiental',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('foto-ambiental-detail.edit', {
            parent: 'foto-ambiental-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foto/foto-ambiental-dialog.html',
                    controller: 'FotoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Foto', function(Foto) {
                            return Foto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('foto-ambiental.new', {
            parent: 'foto-ambiental',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foto/foto-ambiental-dialog.html',
                    controller: 'FotoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                coordenadaa: null,
                                coordenadae: null,
                                coordenadan: null,
                                data: null,
                                descricaoString: null,
                                foto: null,
                                lado: null,
                                link: null,
                                numero: null,
                                picasaId: null,
                                ponto: null,
                                thumb: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('foto-ambiental', null, { reload: 'foto-ambiental' });
                }, function() {
                    $state.go('foto-ambiental');
                });
            }]
        })
        .state('foto-ambiental.edit', {
            parent: 'foto-ambiental',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foto/foto-ambiental-dialog.html',
                    controller: 'FotoAmbientalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Foto', function(Foto) {
                            return Foto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('foto-ambiental', null, { reload: 'foto-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('foto-ambiental.delete', {
            parent: 'foto-ambiental',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/foto/foto-ambiental-delete-dialog.html',
                    controller: 'FotoAmbientalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Foto', function(Foto) {
                            return Foto.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('foto-ambiental', null, { reload: 'foto-ambiental' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
