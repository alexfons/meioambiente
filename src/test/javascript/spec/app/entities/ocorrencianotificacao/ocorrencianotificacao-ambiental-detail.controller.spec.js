'use strict';

describe('Controller Tests', function() {

    describe('Ocorrencianotificacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockOcorrencianotificacao, MockOcorrencia;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockOcorrencianotificacao = jasmine.createSpy('MockOcorrencianotificacao');
            MockOcorrencia = jasmine.createSpy('MockOcorrencia');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Ocorrencianotificacao': MockOcorrencianotificacao,
                'Ocorrencia': MockOcorrencia
            };
            createController = function() {
                $injector.get('$controller')("OcorrencianotificacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:ocorrencianotificacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
