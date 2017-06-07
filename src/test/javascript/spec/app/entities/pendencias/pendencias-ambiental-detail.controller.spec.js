'use strict';

describe('Controller Tests', function() {

    describe('Pendencias Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPendencias, MockObra, MockFoto, MockOcorrenciapendencias;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPendencias = jasmine.createSpy('MockPendencias');
            MockObra = jasmine.createSpy('MockObra');
            MockFoto = jasmine.createSpy('MockFoto');
            MockOcorrenciapendencias = jasmine.createSpy('MockOcorrenciapendencias');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Pendencias': MockPendencias,
                'Obra': MockObra,
                'Foto': MockFoto,
                'Ocorrenciapendencias': MockOcorrenciapendencias
            };
            createController = function() {
                $injector.get('$controller')("PendenciasAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:pendenciasUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
