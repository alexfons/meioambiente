'use strict';

describe('Controller Tests', function() {

    describe('Ocorrenciainforme Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOcorrenciainforme, MockOcorrencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOcorrenciainforme = jasmine.createSpy('MockOcorrenciainforme');
            MockOcorrencia = jasmine.createSpy('MockOcorrencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Ocorrenciainforme': MockOcorrenciainforme,
                'Ocorrencia': MockOcorrencia
            };
            createController = function() {
                $injector.get('$controller')("OcorrenciainformeAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:ocorrenciainformeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
