'use strict';

describe('Controller Tests', function() {

    describe('Auc Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAuc, MockAtividadelicenca, MockOrgaoemissor, MockObra, MockProjeto, MockEmpresa, MockCondicionante, MockFoto, MockDocumento;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAuc = jasmine.createSpy('MockAuc');
            MockAtividadelicenca = jasmine.createSpy('MockAtividadelicenca');
            MockOrgaoemissor = jasmine.createSpy('MockOrgaoemissor');
            MockObra = jasmine.createSpy('MockObra');
            MockProjeto = jasmine.createSpy('MockProjeto');
            MockEmpresa = jasmine.createSpy('MockEmpresa');
            MockCondicionante = jasmine.createSpy('MockCondicionante');
            MockFoto = jasmine.createSpy('MockFoto');
            MockDocumento = jasmine.createSpy('MockDocumento');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Auc': MockAuc,
                'Atividadelicenca': MockAtividadelicenca,
                'Orgaoemissor': MockOrgaoemissor,
                'Obra': MockObra,
                'Projeto': MockProjeto,
                'Empresa': MockEmpresa,
                'Condicionante': MockCondicionante,
                'Foto': MockFoto,
                'Documento': MockDocumento
            };
            createController = function() {
                $injector.get('$controller')("AucAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:aucUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
