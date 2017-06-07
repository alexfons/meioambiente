'use strict';

describe('Controller Tests', function() {

    describe('Contratoobra Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockContratoobra, MockContrato, MockResidente, MockResponsavel;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockContratoobra = jasmine.createSpy('MockContratoobra');
            MockContrato = jasmine.createSpy('MockContrato');
            MockResidente = jasmine.createSpy('MockResidente');
            MockResponsavel = jasmine.createSpy('MockResponsavel');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Contratoobra': MockContratoobra,
                'Contrato': MockContrato,
                'Residente': MockResidente,
                'Responsavel': MockResponsavel
            };
            createController = function() {
                $injector.get('$controller')("ContratoobraAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:contratoobraUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
