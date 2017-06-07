'use strict';

describe('Controller Tests', function() {

    describe('Desapropriacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDesapropriacao, MockPlanocontas, MockFonte, MockCategoriainversao, MockContabancaria, MockReferencia, MockRodovia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDesapropriacao = jasmine.createSpy('MockDesapropriacao');
            MockPlanocontas = jasmine.createSpy('MockPlanocontas');
            MockFonte = jasmine.createSpy('MockFonte');
            MockCategoriainversao = jasmine.createSpy('MockCategoriainversao');
            MockContabancaria = jasmine.createSpy('MockContabancaria');
            MockReferencia = jasmine.createSpy('MockReferencia');
            MockRodovia = jasmine.createSpy('MockRodovia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Desapropriacao': MockDesapropriacao,
                'Planocontas': MockPlanocontas,
                'Fonte': MockFonte,
                'Categoriainversao': MockCategoriainversao,
                'Contabancaria': MockContabancaria,
                'Referencia': MockReferencia,
                'Rodovia': MockRodovia
            };
            createController = function() {
                $injector.get('$controller')("DesapropriacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:desapropriacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
