'use strict';

describe('Controller Tests', function() {

    describe('Informe Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockInforme, MockObra, MockFoto, MockOcorrenciainforme;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockInforme = jasmine.createSpy('MockInforme');
            MockObra = jasmine.createSpy('MockObra');
            MockFoto = jasmine.createSpy('MockFoto');
            MockOcorrenciainforme = jasmine.createSpy('MockOcorrenciainforme');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Informe': MockInforme,
                'Obra': MockObra,
                'Foto': MockFoto,
                'Ocorrenciainforme': MockOcorrenciainforme
            };
            createController = function() {
                $injector.get('$controller')("InformeAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:informeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
