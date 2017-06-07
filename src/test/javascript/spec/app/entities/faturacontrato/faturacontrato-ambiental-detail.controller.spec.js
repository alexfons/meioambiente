'use strict';

describe('Controller Tests', function() {

    describe('Faturacontrato Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFaturacontrato, MockFonte, MockContrato, MockReferenciacontrato;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFaturacontrato = jasmine.createSpy('MockFaturacontrato');
            MockFonte = jasmine.createSpy('MockFonte');
            MockContrato = jasmine.createSpy('MockContrato');
            MockReferenciacontrato = jasmine.createSpy('MockReferenciacontrato');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Faturacontrato': MockFaturacontrato,
                'Fonte': MockFonte,
                'Contrato': MockContrato,
                'Referenciacontrato': MockReferenciacontrato
            };
            createController = function() {
                $injector.get('$controller')("FaturacontratoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:faturacontratoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
