'use strict';

describe('Controller Tests', function() {

    describe('Obra Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockObra, MockTipoObra, MockInspetor, MockFiscal, MockTrecho, MockContratoobra, MockHistorico;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockObra = jasmine.createSpy('MockObra');
            MockTipoObra = jasmine.createSpy('MockTipoObra');
            MockInspetor = jasmine.createSpy('MockInspetor');
            MockFiscal = jasmine.createSpy('MockFiscal');
            MockTrecho = jasmine.createSpy('MockTrecho');
            MockContratoobra = jasmine.createSpy('MockContratoobra');
            MockHistorico = jasmine.createSpy('MockHistorico');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Obra': MockObra,
                'TipoObra': MockTipoObra,
                'Inspetor': MockInspetor,
                'Fiscal': MockFiscal,
                'Trecho': MockTrecho,
                'Contratoobra': MockContratoobra,
                'Historico': MockHistorico
            };
            createController = function() {
                $injector.get('$controller')("ObraAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:obraUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
