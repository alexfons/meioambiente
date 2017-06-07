'use strict';

describe('Controller Tests', function() {

    describe('Fatura Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFatura, MockFonte, MockContabancaria, MockContrato, MockReferencia, MockMedicao;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFatura = jasmine.createSpy('MockFatura');
            MockFonte = jasmine.createSpy('MockFonte');
            MockContabancaria = jasmine.createSpy('MockContabancaria');
            MockContrato = jasmine.createSpy('MockContrato');
            MockReferencia = jasmine.createSpy('MockReferencia');
            MockMedicao = jasmine.createSpy('MockMedicao');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Fatura': MockFatura,
                'Fonte': MockFonte,
                'Contabancaria': MockContabancaria,
                'Contrato': MockContrato,
                'Referencia': MockReferencia,
                'Medicao': MockMedicao
            };
            createController = function() {
                $injector.get('$controller')("FaturaAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:faturaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
