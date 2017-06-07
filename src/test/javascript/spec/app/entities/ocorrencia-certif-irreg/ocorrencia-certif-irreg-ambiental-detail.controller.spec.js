'use strict';

describe('Controller Tests', function() {

    describe('OcorrenciaCertifIrreg Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOcorrenciaCertifIrreg, MockOcorrencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOcorrenciaCertifIrreg = jasmine.createSpy('MockOcorrenciaCertifIrreg');
            MockOcorrencia = jasmine.createSpy('MockOcorrencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'OcorrenciaCertifIrreg': MockOcorrenciaCertifIrreg,
                'Ocorrencia': MockOcorrencia
            };
            createController = function() {
                $injector.get('$controller')("OcorrenciaCertifIrregAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:ocorrenciaCertifIrregUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
