'use strict';

describe('Controller Tests', function() {

    describe('Pagfuncionario Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPagfuncionario, MockPlanocontas, MockFonte, MockCategoriainversao, MockFuncionario, MockReferencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPagfuncionario = jasmine.createSpy('MockPagfuncionario');
            MockPlanocontas = jasmine.createSpy('MockPlanocontas');
            MockFonte = jasmine.createSpy('MockFonte');
            MockCategoriainversao = jasmine.createSpy('MockCategoriainversao');
            MockFuncionario = jasmine.createSpy('MockFuncionario');
            MockReferencia = jasmine.createSpy('MockReferencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Pagfuncionario': MockPagfuncionario,
                'Planocontas': MockPlanocontas,
                'Fonte': MockFonte,
                'Categoriainversao': MockCategoriainversao,
                'Funcionario': MockFuncionario,
                'Referencia': MockReferencia
            };
            createController = function() {
                $injector.get('$controller')("PagfuncionarioAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:pagfuncionarioUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
