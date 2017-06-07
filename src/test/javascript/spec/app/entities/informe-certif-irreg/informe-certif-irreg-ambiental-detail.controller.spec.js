'use strict';

describe('Controller Tests', function() {

    describe('InformeCertifIrreg Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockInformeCertifIrreg, MockInforme;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockInformeCertifIrreg = jasmine.createSpy('MockInformeCertifIrreg');
            MockInforme = jasmine.createSpy('MockInforme');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'InformeCertifIrreg': MockInformeCertifIrreg,
                'Informe': MockInforme
            };
            createController = function() {
                $injector.get('$controller')("InformeCertifIrregAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:informeCertifIrregUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
