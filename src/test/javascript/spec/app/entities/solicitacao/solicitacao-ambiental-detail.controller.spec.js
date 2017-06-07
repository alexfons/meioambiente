'use strict';

describe('Controller Tests', function() {

    describe('Solicitacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockSolicitacao, MockBanco, MockContratoagente;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockSolicitacao = jasmine.createSpy('MockSolicitacao');
            MockBanco = jasmine.createSpy('MockBanco');
            MockContratoagente = jasmine.createSpy('MockContratoagente');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Solicitacao': MockSolicitacao,
                'Banco': MockBanco,
                'Contratoagente': MockContratoagente
            };
            createController = function() {
                $injector.get('$controller')("SolicitacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:solicitacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
