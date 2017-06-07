'use strict';

describe('Controller Tests', function() {

    describe('Certificadoconformidade Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCertificadoconformidade, MockObra, MockTipocertificadoconformidade, MockInformecertificadoirregularidade, MockNotificacaocertificadoirregularidade, MockOcorrenciacertificadoirregularidade;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCertificadoconformidade = jasmine.createSpy('MockCertificadoconformidade');
            MockObra = jasmine.createSpy('MockObra');
            MockTipocertificadoconformidade = jasmine.createSpy('MockTipocertificadoconformidade');
            MockInformecertificadoirregularidade = jasmine.createSpy('MockInformecertificadoirregularidade');
            MockNotificacaocertificadoirregularidade = jasmine.createSpy('MockNotificacaocertificadoirregularidade');
            MockOcorrenciacertificadoirregularidade = jasmine.createSpy('MockOcorrenciacertificadoirregularidade');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Certificadoconformidade': MockCertificadoconformidade,
                'Obra': MockObra,
                'Tipocertificadoconformidade': MockTipocertificadoconformidade,
                'Informecertificadoirregularidade': MockInformecertificadoirregularidade,
                'Notificacaocertificadoirregularidade': MockNotificacaocertificadoirregularidade,
                'Ocorrenciacertificadoirregularidade': MockOcorrenciacertificadoirregularidade
            };
            createController = function() {
                $injector.get('$controller')("CertificadoconformidadeAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:certificadoconformidadeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
