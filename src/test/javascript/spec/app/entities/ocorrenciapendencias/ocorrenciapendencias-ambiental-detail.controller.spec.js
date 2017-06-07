'use strict';

describe('Controller Tests', function() {

    describe('Ocorrenciapendencias Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOcorrenciapendencias, MockOcorrencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOcorrenciapendencias = jasmine.createSpy('MockOcorrenciapendencias');
            MockOcorrencia = jasmine.createSpy('MockOcorrencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Ocorrenciapendencias': MockOcorrenciapendencias,
                'Ocorrencia': MockOcorrencia
            };
            createController = function() {
                $injector.get('$controller')("OcorrenciapendenciasAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:ocorrenciapendenciasUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
