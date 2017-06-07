'use strict';

describe('Controller Tests', function() {

    describe('Licenca Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockLicenca, MockAtividadelicenca, MockEmpresa, MockProjeto, MockTipolicenca, MockObra, MockOrgaoemissor, MockCondicionante, MockDocumento, MockFoto;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockLicenca = jasmine.createSpy('MockLicenca');
            MockAtividadelicenca = jasmine.createSpy('MockAtividadelicenca');
            MockEmpresa = jasmine.createSpy('MockEmpresa');
            MockProjeto = jasmine.createSpy('MockProjeto');
            MockTipolicenca = jasmine.createSpy('MockTipolicenca');
            MockObra = jasmine.createSpy('MockObra');
            MockOrgaoemissor = jasmine.createSpy('MockOrgaoemissor');
            MockCondicionante = jasmine.createSpy('MockCondicionante');
            MockDocumento = jasmine.createSpy('MockDocumento');
            MockFoto = jasmine.createSpy('MockFoto');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Licenca': MockLicenca,
                'Atividadelicenca': MockAtividadelicenca,
                'Empresa': MockEmpresa,
                'Projeto': MockProjeto,
                'Tipolicenca': MockTipolicenca,
                'Obra': MockObra,
                'Orgaoemissor': MockOrgaoemissor,
                'Condicionante': MockCondicionante,
                'Documento': MockDocumento,
                'Foto': MockFoto
            };
            createController = function() {
                $injector.get('$controller')("LicencaAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:licencaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
