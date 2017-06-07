'use strict';

describe('Controller Tests', function() {

    describe('Projeto Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockProjeto, MockInspetor, MockMunicipio, MockTipoobra, MockTrecho, MockFiscal, MockContratoprojeto, MockHistorico;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockProjeto = jasmine.createSpy('MockProjeto');
            MockInspetor = jasmine.createSpy('MockInspetor');
            MockMunicipio = jasmine.createSpy('MockMunicipio');
            MockTipoobra = jasmine.createSpy('MockTipoobra');
            MockTrecho = jasmine.createSpy('MockTrecho');
            MockFiscal = jasmine.createSpy('MockFiscal');
            MockContratoprojeto = jasmine.createSpy('MockContratoprojeto');
            MockHistorico = jasmine.createSpy('MockHistorico');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Projeto': MockProjeto,
                'Inspetor': MockInspetor,
                'Municipio': MockMunicipio,
                'Tipoobra': MockTipoobra,
                'Trecho': MockTrecho,
                'Fiscal': MockFiscal,
                'Contratoprojeto': MockContratoprojeto,
                'Historico': MockHistorico
            };
            createController = function() {
                $injector.get('$controller')("ProjetoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:projetoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
