'use strict';

describe('Controller Tests', function() {

    describe('Valoresdesembolso Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockValoresdesembolso, MockContabancaria, MockReferencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockValoresdesembolso = jasmine.createSpy('MockValoresdesembolso');
            MockContabancaria = jasmine.createSpy('MockContabancaria');
            MockReferencia = jasmine.createSpy('MockReferencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Valoresdesembolso': MockValoresdesembolso,
                'Contabancaria': MockContabancaria,
                'Referencia': MockReferencia
            };
            createController = function() {
                $injector.get('$controller')("ValoresdesembolsoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:valoresdesembolsoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
