'use strict';

describe('Controller Tests', function() {

    describe('Contrato Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockContrato, MockEmpresa, MockPlanocontas, MockNatureza, MockProposta, MockCategoriainversao, MockAditivocontrato, MockContratoobra, MockEmpresacontrato;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockContrato = jasmine.createSpy('MockContrato');
            MockEmpresa = jasmine.createSpy('MockEmpresa');
            MockPlanocontas = jasmine.createSpy('MockPlanocontas');
            MockNatureza = jasmine.createSpy('MockNatureza');
            MockProposta = jasmine.createSpy('MockProposta');
            MockCategoriainversao = jasmine.createSpy('MockCategoriainversao');
            MockAditivocontrato = jasmine.createSpy('MockAditivocontrato');
            MockContratoobra = jasmine.createSpy('MockContratoobra');
            MockEmpresacontrato = jasmine.createSpy('MockEmpresacontrato');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Contrato': MockContrato,
                'Empresa': MockEmpresa,
                'Planocontas': MockPlanocontas,
                'Natureza': MockNatureza,
                'Proposta': MockProposta,
                'Categoriainversao': MockCategoriainversao,
                'Aditivocontrato': MockAditivocontrato,
                'Contratoobra': MockContratoobra,
                'Empresacontrato': MockEmpresacontrato
            };
            createController = function() {
                $injector.get('$controller')("ContratoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:contratoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
