'use strict';

describe('Controller Tests', function() {

    describe('Planoaquisicoes Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPlanoaquisicoes, MockFonte, MockContratoagente, MockEdital;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPlanoaquisicoes = jasmine.createSpy('MockPlanoaquisicoes');
            MockFonte = jasmine.createSpy('MockFonte');
            MockContratoagente = jasmine.createSpy('MockContratoagente');
            MockEdital = jasmine.createSpy('MockEdital');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Planoaquisicoes': MockPlanoaquisicoes,
                'Fonte': MockFonte,
                'Contratoagente': MockContratoagente,
                'Edital': MockEdital
            };
            createController = function() {
                $injector.get('$controller')("PlanoaquisicoesAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:planoaquisicoesUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
