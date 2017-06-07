'use strict';

describe('Controller Tests', function() {

    describe('CertifConfor Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockCertifConfor, MockObra, MockTipocertifConfor, MockInformeCertifIrreg, MockNotificacaoCertifIrreg, MockOcorrenciaCertifIrreg;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockCertifConfor = jasmine.createSpy('MockCertifConfor');
            MockObra = jasmine.createSpy('MockObra');
            MockTipocertifConfor = jasmine.createSpy('MockTipocertifConfor');
            MockInformeCertifIrreg = jasmine.createSpy('MockInformeCertifIrreg');
            MockNotificacaoCertifIrreg = jasmine.createSpy('MockNotificacaoCertifIrreg');
            MockOcorrenciaCertifIrreg = jasmine.createSpy('MockOcorrenciaCertifIrreg');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'CertifConfor': MockCertifConfor,
                'Obra': MockObra,
                'TipocertifConfor': MockTipocertifConfor,
                'InformeCertifIrreg': MockInformeCertifIrreg,
                'NotificacaoCertifIrreg': MockNotificacaoCertifIrreg,
                'OcorrenciaCertifIrreg': MockOcorrenciaCertifIrreg
            };
            createController = function() {
                $injector.get('$controller')("CertifConforAmbientalDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'meioambienteApp:certifConforUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
