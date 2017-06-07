'use strict';

describe('Controller Tests', function() {

    describe('Clausulascontratuais Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockClausulascontratuais, MockContratoagente;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockClausulascontratuais = jasmine.createSpy('MockClausulascontratuais');
            MockContratoagente = jasmine.createSpy('MockContratoagente');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Clausulascontratuais': MockClausulascontratuais,
                'Contratoagente': MockContratoagente
            };
            createController = function() {
                $injector.get('$controller')("ClausulascontratuaisAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:clausulascontratuaisUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
