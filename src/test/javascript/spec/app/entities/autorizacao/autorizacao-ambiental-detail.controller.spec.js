'use strict';

describe('Controller Tests', function() {

    describe('Autorizacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAutorizacao, MockAtividadelicenca, MockEmpresa, MockObra, MockOrgaoemissor, MockProjeto, MockTipoautorizacao, MockFoto, MockDocumento;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAutorizacao = jasmine.createSpy('MockAutorizacao');
            MockAtividadelicenca = jasmine.createSpy('MockAtividadelicenca');
            MockEmpresa = jasmine.createSpy('MockEmpresa');
            MockObra = jasmine.createSpy('MockObra');
            MockOrgaoemissor = jasmine.createSpy('MockOrgaoemissor');
            MockProjeto = jasmine.createSpy('MockProjeto');
            MockTipoautorizacao = jasmine.createSpy('MockTipoautorizacao');
            MockFoto = jasmine.createSpy('MockFoto');
            MockDocumento = jasmine.createSpy('MockDocumento');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Autorizacao': MockAutorizacao,
                'Atividadelicenca': MockAtividadelicenca,
                'Empresa': MockEmpresa,
                'Obra': MockObra,
                'Orgaoemissor': MockOrgaoemissor,
                'Projeto': MockProjeto,
                'Tipoautorizacao': MockTipoautorizacao,
                'Foto': MockFoto,
                'Documento': MockDocumento
            };
            createController = function() {
                $injector.get('$controller')("AutorizacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:autorizacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
