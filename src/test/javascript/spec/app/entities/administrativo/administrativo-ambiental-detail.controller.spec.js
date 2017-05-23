'use strict';

describe('Controller Tests', function() {

    describe('Administrativo Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAdministrativo, MockTipoadministrativo, MockObra, MockFoto, MockDocumento, MockParticipante;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAdministrativo = jasmine.createSpy('MockAdministrativo');
            MockTipoadministrativo = jasmine.createSpy('MockTipoadministrativo');
            MockObra = jasmine.createSpy('MockObra');
            MockFoto = jasmine.createSpy('MockFoto');
            MockDocumento = jasmine.createSpy('MockDocumento');
            MockParticipante = jasmine.createSpy('MockParticipante');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Administrativo': MockAdministrativo,
                'Tipoadministrativo': MockTipoadministrativo,
                'Obra': MockObra,
                'Foto': MockFoto,
                'Documento': MockDocumento,
                'Participante': MockParticipante
            };
            createController = function() {
                $injector.get('$controller')("AdministrativoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:administrativoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
