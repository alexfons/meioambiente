'use strict';

describe('Controller Tests', function() {

    describe('Notificacao Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockNotificacao, MockObra, MockFoto, MockOcorrencianotificacao;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockNotificacao = jasmine.createSpy('MockNotificacao');
            MockObra = jasmine.createSpy('MockObra');
            MockFoto = jasmine.createSpy('MockFoto');
            MockOcorrencianotificacao = jasmine.createSpy('MockOcorrencianotificacao');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Notificacao': MockNotificacao,
                'Obra': MockObra,
                'Foto': MockFoto,
                'Ocorrencianotificacao': MockOcorrencianotificacao
            };
            createController = function() {
                $injector.get('$controller')("NotificacaoAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:notificacaoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
