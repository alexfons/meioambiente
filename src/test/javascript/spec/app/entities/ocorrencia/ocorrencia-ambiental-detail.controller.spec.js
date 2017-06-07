'use strict';

describe('Controller Tests', function() {

    describe('Ocorrencia Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOcorrencia, MockAtividade, MockObra, MockTabela, MockTipoocorrencia, MockFoto, MockHistorico, MockRegistro;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOcorrencia = jasmine.createSpy('MockOcorrencia');
            MockAtividade = jasmine.createSpy('MockAtividade');
            MockObra = jasmine.createSpy('MockObra');
            MockTabela = jasmine.createSpy('MockTabela');
            MockTipoocorrencia = jasmine.createSpy('MockTipoocorrencia');
            MockFoto = jasmine.createSpy('MockFoto');
            MockHistorico = jasmine.createSpy('MockHistorico');
            MockRegistro = jasmine.createSpy('MockRegistro');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Ocorrencia': MockOcorrencia,
                'Atividade': MockAtividade,
                'Obra': MockObra,
                'Tabela': MockTabela,
                'Tipoocorrencia': MockTipoocorrencia,
                'Foto': MockFoto,
                'Historico': MockHistorico,
                'Registro': MockRegistro
            };
            createController = function() {
                $injector.get('$controller')("OcorrenciaAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:ocorrenciaUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
